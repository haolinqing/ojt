package jp.co.ntt.ojt.domain.model;

import java.io.Serializable;

public class Role implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -321071913194636748L;

	/**
	 * ユーザ
	 */
	private User user;
	
	/**
	 * 役割名
	 */
	private String roleName;

	/**
	 * ユーザを取得します。
	 * @return ユーザ
	 */
	public User getUser() {
		return user;
	}

	/**
	 * ユーザを設定します。
	 * @param user ユーザ
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 役割名を取得します。
	 * @return 役割名
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 役割名を設定します。
	 * @param roleName 役割名
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
