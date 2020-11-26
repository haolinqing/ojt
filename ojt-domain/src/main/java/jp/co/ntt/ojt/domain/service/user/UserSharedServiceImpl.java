package jp.co.ntt.ojt.domain.service.user;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.ojt.domain.model.User;
import jp.co.ntt.ojt.domain.repository.user.UserRepository;

/**
 * ユーザに関するサービス実装.
 */
@Service
@Transactional(readOnly = true)
public class UserSharedServiceImpl implements UserSharedService {

	/**
	 * ユーザRepository
	 */
	@Inject
	private UserRepository userRepository;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findOneByUserId(String userId) {
		User user = userRepository.findOneByUserId(userId);
		if(user == null || user.getStatus().equals(User.RMVD)) {
			ResultMessages messages = ResultMessages.error();
			messages.add(ResultMessage.fromCode("e.xx.fw.2001",user.getUserId()));
			throw new ResourceNotFoundException(messages);
		}
		return user;
	}

}
