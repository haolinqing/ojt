package jp.co.ntt.ojt.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public class User implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 95496152463607605L;

	/**
	 * ACTV 有効状態
	 */
	public static final String ACTV = "ACTV";

	/**
	 * INIT 初期状態
	 */
	public static final String INIT = "INIT";

	/**
	 * RMVD 削除済み
	 */
	public static final String RMVD = "RMVD";

	/**
	 * ユーザID
	 */
	private String userId;

	/**
	 * 名前
	 */
	private String username;

	/**
	 * 生年月日
	 */
	private LocalDate birthday;

	/**
	 * 住所
	 */
	private String address;

	/**
	 * 電話番号
	 */
	private String telephone;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * 状態
	 */
	private String status;

	/**
	 * 最終更新日時
	 */
	private LocalDateTime lastUpdateTime;

	/**
	 * 複数権限
	 */
	private List<Role> roles = new ArrayList<>();

	/**
	 * ユーザＩＤを取得します。
	 * 
	 * @return ユーザＩＤ
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザＩＤを設定します。
	 * 
	 * @param userId ユーザＩＤ
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 名前を取得します。
	 * 
	 * @return 名前
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 名前を設定します。
	 * 
	 * @param username 名前
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 生年月日を取得します。
	 * 
	 * @return 生年月日
	 */
	public LocalDate getBirthday() {
		return birthday;
	}

	/**
	 * 生年月日を設定します。
	 * 
	 * @param birthday 生年月日
	 */
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * 住所を取得します。
	 * 
	 * @return 住所
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 住所を設定します。
	 * 
	 * @param address 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 電話番号を取得します。
	 * 
	 * @return 電話番号
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 電話番号を設定します。
	 * 
	 * @param telephone 電話番号
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * パスワードを取得します。
	 * 
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定します。
	 * 
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 状態を取得します。
	 * 
	 * @return 状態
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 状態を設定します。
	 * 
	 * @param status 状態
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 最終更新日時を取得します。
	 * 
	 * @return 最終更新日時
	 */
	public LocalDateTime getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * 最終更新日時を設定します。
	 * 
	 * @param lastUpdateTime 最終更新日時
	 */
	public void setLastUpdateTime(LocalDateTime lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * 複数権限を取得します。
	 * 
	 * @return 複数権限
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * 複数権限を設定します。
	 * 
	 * @param roles 複数権限
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 複数名前権限を取得します。
	 * 
	 * @return 複数権限名前
	 */
	public String[] getRoleNames() {
		if (roles == null || roles.size() == 0) {
			return null;
		}
		ArrayList<String> roleNames = new ArrayList<>();
		for (Role role : roles) {
			roleNames.add(role.getRoleName());
		}
		return roleNames.toArray(new String[0]);
	}

	/**
	 * 複数権限名前を設定します。
	 * 
	 * @param roleNames 複数権限名前
	 */
	public void setRoleNames(String[] roleNames) {
		if (roleNames != null) {
			List<Role> roles = new ArrayList<>();
			Role role = null;
			for (String roleName : roleNames) {
				role = new Role();
				role.setRoleName(roleName);
				role.setUser(this);
				roles.add(role);
			}
			this.roles = roles;
		}

	}

}
