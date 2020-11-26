package jp.co.ntt.ojt.domain.service.user;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

import jp.co.ntt.ojt.domain.model.User;

/**
 * 予約ユーザ詳細情報取得サービス.
 *
 */
@Service
@Transactional(readOnly = true)
public class LoginUserDetailsService implements UserDetailsService {
	
	/**
	 * ユーザサービス.
	 */
	@Inject
	private UserSharedService userSharedService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		try {
			User user = userSharedService.findOneByUserId(userId);
			return new LoginUserDetails(user);
		}catch(ResourceNotFoundException e) {
			throw new UsernameNotFoundException("ユーザーが見つかりません.ユーザーID:" + userId);
		}
	}

}
