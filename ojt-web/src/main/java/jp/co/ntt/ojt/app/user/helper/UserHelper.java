package jp.co.ntt.ojt.app.user.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.ntt.ojt.app.user.UserForm;
import jp.co.ntt.ojt.domain.model.Role;
import jp.co.ntt.ojt.domain.model.User;

/**
 * User Helper インタフェース.
 */
@Component
public class UserHelper {
	
	/**
	 * ユーザフォームからユーザへの変換
	 * 
	 * @param userForm ユーザフォーム
	 * @return user ユーザ
	 */
	public User appleUser(UserForm userForm) {
		User user = new User();
		
		user.setUserId(userForm.getUserId());
		user.setUsername(userForm.getUsername());
		user.setBirthday(userForm.getJodaBirthday());
		user.setAddress(userForm.getAddress());
		user.setTelephone(userForm.getTelephone());
		
		String[] roleNames = userForm.getRoleNames();
		List<Role> roles = new ArrayList<>();
		if(roleNames != null) {
			Role role = null;
			for(String roleName : roleNames) {
				role = new Role();
				role.setRoleName(roleName);
				roles.add(role);
			}
		}
		user.setRoles(roles);
		user.setStatus(userForm.getStatus());
		
		user.setPassword(userForm.getPassword());
		
		user.setLastUpdateTime(userForm.getJodaLastUpdateTime());
		
		return user;
	}
	
	/**
	 * ユーザフォームからユーザへの変換
	 * 
	 * @param user ユーザ
	 * @return userForm ユーザフォーム
	 */
	public UserForm appleUserForm(User user) {
		UserForm userForm = new UserForm();
		
		userForm.setUserId(user.getUserId());
		userForm.setUsername(user.getUsername());
		userForm.setBirthday(user.getBirthday().toDate());
		userForm.setAddress(user.getAddress());
		userForm.setTelephone(user.getTelephone());
		userForm.setRoleNames(user.getRoleNames());
		
		userForm.setLastUpdateTime(user.getLastUpdateTime().toDate());
		
		return userForm;
	}

}
