package jp.co.ntt.ojt.domain.service.user;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.ojt.domain.model.Role;
import jp.co.ntt.ojt.domain.model.User;
import jp.co.ntt.ojt.domain.repository.role.RoleRepository;
import jp.co.ntt.ojt.domain.repository.user.UserRepository;

/**
 *　ユーザサービス実装. 
 */
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {
	
	/**
	 * ユーザRepository
	 */
	@Inject
	private UserRepository userRepository;
	
	/**
	 * ユーザ共通Repository
	 */
	@Inject
	private UserSharedService userSharedService;
	
	/**
	 * ロールRepository
	 */
	@Inject
	private RoleRepository roleRepository;

	/**
	 * 条件によるとユーザー情報を検索する
	 */
	@Override
	public Page<User> findPageByInfo(User user,Pageable pageable) {
		long total = userRepository.countByInfo(user);
		if(total <= 0) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromCode("e.xx.se.2001"));
			throw new BusinessException(messages);
		}
		List<User> userList = userRepository.findPageByInfo(user,pageable);
		return new PageImpl<User>(userList, pageable, total);
	}

	/**
	 * ユーザーIDによるとユーザー情報を削除する
	 */
	@Override
	@Transactional(readOnly = false)
	@PreAuthorize("hasRole('ROLE_ADMN')")
	public void delete(User user) {
		if(!userRepository.delete(user)) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromCode("e.xx.de.2001",user.getUserId()));
			throw new BusinessException(messages);
		}
	}

	/**
	 * ユーザーIDによるユーザー情報を検索する
	 */
	@Override
	public User findOneByUserId(String userId) {
		return userSharedService.findOneByUserId(userId);
	}

	/**
	 * 更新機能
	 */
	@Override
	@Transactional(readOnly = false)
	@PreAuthorize("hasRole('ROLE_ADMN')")
	public void update(User user) {
		
		String password = user.getPassword();
		if(password != null || !"".equals(password)) {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			/* password = "{bcrypt}" + bCryptPasswordEncoder.encode(password); */
			password =  bCryptPasswordEncoder.encode(password);
			user.setPassword(password);
		}
		
		boolean isSuccess = userRepository.update(user);
		if(!isSuccess) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromCode("e.xx.up.2001",user.getUserId()));
			throw new BusinessException(messages);
		}
		
		List<Role> roles = roleRepository.findByUserId(user.getUserId());
		List<String> roleNames = Arrays.asList(user.getRoleNames());
		
		for (String roleName : roleNames) {
			boolean isExist = false;
			for (Role role : roles) {
				if(roleName.equals(role.getRoleName())) {
					isExist = true;
					break;
				}
			}
			if(!isExist) {
				isSuccess = roleRepository.insert(user.getUserId(), roleName);
				if(!isSuccess) {
					ResultMessages messages = ResultMessages.error();
					messages.add(ResultMessage.fromCode("e.xx.up.2001",user.getUserId()));
					throw new BusinessException(messages);
				}
			}
		}
		
		for (Role role : roles) {
			boolean isExist = false;
			for (String roleName : roleNames) {
				if(roleName.equals(role.getRoleName())) {
					isExist = true;
					break;
				}
			}
			if(!isExist) {
				if(!roleRepository.delete(user.getUserId(), role.getRoleName())) {
					ResultMessages messages = ResultMessages.error();
					messages.add(ResultMessage.fromCode("e.xx.up.2001",user.getUserId()));
					throw new BusinessException(messages);
				}
			}
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	@PreAuthorize("hasRole('ROLE_ADMN')")
	public void register(User user) {
		String userId = user.getUserId();
		if(userId != null && userId.equals("")) {
			TreeSet<Integer> userIds = userRepository.findAllUserId();
			if(userIds.size() > 9999) {
				ResultMessages messages = ResultMessages.error();
				messages.add(ResultMessage.fromCode("e.xx.re.2001"));
				throw new BusinessException(messages);
			}
			TreeSet<Integer> userIdSeq = new TreeSet<>(Stream.iterate(0, item -> item+1).limit(9999).collect(Collectors.toList()));
			userIdSeq.removeAll(userIds);
			Integer newUserId = userIdSeq.first();
			userId = String.format("%04d", newUserId);
			user.setUserId(userId);
		}else if(userRepository.findOneByUserId(userId) != null) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromCode("e.xx.re.2002"));
			throw new BusinessException(messages);
		}
		String password = user.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		/* password = "{bcrypt}" + bCryptPasswordEncoder.encode(password); */
		password =  bCryptPasswordEncoder.encode(password);
		user.setPassword(password);
		boolean isSuccess = userRepository.insert(user);
		
		if(!isSuccess) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromCode("e.xx.re.2003"));
			throw new BusinessException(messages);
		}

		String[] roleNames = user.getRoleNames();
		for (String roleName : roleNames) {
			isSuccess = roleRepository.insert(user.getUserId(), roleName);
			if(!isSuccess) {
				ResultMessages messages = ResultMessages.error();
				messages.add(ResultMessage.fromCode("e.xx.re.2003"));
				throw new BusinessException(messages);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false)
	public void initialization(User user) {
		
		LoginUserDetails userDetails = (LoginUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User loginer = userDetails.getUser();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		boolean isSame = bCryptPasswordEncoder.matches(user.getPassword(),loginer.getPassword().substring(8));
		if(isSame) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromCode("e.xx.pw.2004"));
			throw new BusinessException(messages);
		}
		
		user.setPassword( bCryptPasswordEncoder.encode(user.getPassword()));
		boolean isSuccess = userRepository.updatePasswordAndStatus(user);
		if(!isSuccess) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromCode("e.xx.pw.2003"));
			throw new BusinessException(messages);
		}
		userDetails.getUser().setStatus(User.ACTV);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isExistOfUserId(String userId) {
		User user = userRepository.findOneByUserId(userId);
		if(user != null) {
			return true;
		}
		return false;
	}

}
