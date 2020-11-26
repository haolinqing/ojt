package jp.co.ntt.ojt.domain.service.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import jp.co.ntt.ojt.domain.model.User;

/**
 * ユーザ認証詳細
 */
public class LoginUserDetails implements UserDetails {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7529417413397688966L;
	
	/**
	 * ユーザ.
	 */
	private User user;
	
	/**
	 * コンストラクタ.
	 * 
	 * @param user ユーザ
	 */
	public LoginUserDetails(User user) {
		this.user = user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String[] roles = user.getRoleNames();
		if(roles == null) {
			return null;
		}
		for(int i = 0 ; i < roles.length ; i++) {
			roles[i] = "ROLE_" + roles[i];
		}
		return AuthorityUtils.createAuthorityList(roles);
	}

	/**
	 * ユーザ.を取得します。
	 * @return ユーザ.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
