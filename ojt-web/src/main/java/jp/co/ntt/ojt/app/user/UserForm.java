package jp.co.ntt.ojt.app.user;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.terasoluna.gfw.common.codelist.ExistInCodeList;

import jp.co.ntt.ojt.common.validation.Confirm;

@Confirm(field = "password",confirmField = "repassword")
public class UserForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ログイン処理用の入力チェックルール
	 *
	 */
	public static interface Login{}
	
	/**
	 * 検索処理用の入力チェックルール
	 *
	 */
	public static interface Search{}
	
	/**
	 * 更新処理用の入力チェックルール
	 *
	 */
	public static interface Update{}
	
	/**
	 * 登録処理用の入力チェックルール
	 *
	 */
	public static interface Register{}
	
	/**
	 * パスワード変更処理用の入力チェックルール
	 *
	 */
	public static interface PasswordChange{}
	
	/**
	 * ユーザID
	 */
	@NotNull(groups = {Login.class,Update.class,PasswordChange.class})
	@Pattern(regexp = "([0-9]{4}|)",groups = {Update.class,PasswordChange.class}, message = "{Pattern.UserForm.userId.Update&PasswordChange}")
	@Pattern(regexp = "([0-9]{4}|)",groups = {Search.class,Register.class}, message = "{Pattern.UserForm.userId.Search&Register}")
	private String userId;

	/**
	 * 名前
	 */
	@NotNull(groups = {Update.class,Register.class})
	@Size(max = 30)
	private String username;
	
	/**
	 * 生年月日
	 */
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@NotNull(groups = {Update.class,Register.class})
	private Date birthday;
	
	/**
	 * 住所
	 */
	@NotNull(groups = {Update.class,Register.class})
	@Size(max = 60)
	private String address;
	
	/**
	 * 電話番号
	 */
	@NotNull(groups = {Update.class,Register.class})
	@Pattern(regexp="([0-9]{10,11}|)",groups = {Update.class,Register.class,Search.class},message = "{Pattern.UserForm.telephone}")
	private String telephone;
	
	/**
	 * パスワード
	 */
	@NotNull(groups = {Login.class,Register.class,PasswordChange.class})
	@Pattern(regexp="([0-9a-zA-Z]{6,}|)",groups = {Register.class,PasswordChange.class}, message = "{Pattern.UserForm.password.Register&PasswordChange}")
	@Pattern(regexp="([0-9a-zA-Z]{6,}|)",groups = {Update.class}, message = "{Pattern.UserForm.password.Update}")
	private String password;
	
	/**
	 * 確認パスワード
	 */
	private String repassword;
	
	/**
	 * 状態
	 */
	@ExistInCodeList(codeListId = "CL_STATUS",groups = {Search.class})
	private String status;
	
	/**
	 * 最終更新日時
	 */
	@NotNull(groups = {Update.class,PasswordChange.class})
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private Date lastUpdateTime;
	
	/**
	 * 複数名前権限
	 */
	@Size(min = 1,groups = {Update.class,Register.class})
	private String[] roleNames;

	/**
	 * ユーザＩＤを取得します。
	 * @return ユーザＩＤ
	 */
	public String getUserId() {
		if(userId == null) {
			return null;
		}
		return userId.trim();
	}

	/**
	 * ユーザＩＤを設定します。
	 * @param userId ユーザＩＤ
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 名前を取得します。
	 * @return 名前
	 */
	public String getUsername() {
		if(username == null) {
			return null;
		}
		return username.trim();
	}
	
	/**
	 * 名前を設定します。
	 * @param username 名前
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 生年月日(java.util.Date)を取得します。
	 * @return 生年月日
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 生年月日(org.joda.time.LocalDate)を取得します。
	 * @return 生年月日
	 */
	public LocalDate getJodaBirthday() {
		if(birthday != null) {
			return LocalDate.fromDateFields(birthday);			
		}
		return null;
	}
	
	/**
	 * 生年月日(java.util.Date)を設定します。
	 * @param birthday 生年月日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * 住所を取得します。
	 * @return 住所
	 */
	public String getAddress() {
		if(address == null) {
			return null;
		}
		return address.trim();
	}

	/**
	 * 住所を設定します。
	 * @param address 住所
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 電話番号を取得します。
	 * @return 電話番号
	 */
	public String getTelephone() {
		if(telephone == null) {
			return null;
		}
		return telephone.trim();
	}

	/**
	 * 電話番号を設定します。
	 * @param telephone 電話番号
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * パスワードを取得します。
	 * @return パスワード
	 */
	public String getPassword() {
		if(password == null) {
			return null;
		}
		return password.trim();
	}

	/**
	 * パスワードを設定します。
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 状態を取得します。
	 * @return 状態
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 状態を設定します。
	 * @param status 状態
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 最終更新日時(java.util.Date)を取得します。
	 * @return 最終更新日時
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	/**
	 * 最終更新日時(org.joda.time.LocalDateTime)を取得します。
	 * @return 最終更新日時
	 */
	public LocalDateTime getJodaLastUpdateTime() {
		if(lastUpdateTime != null) {
			return LocalDateTime.fromDateFields(lastUpdateTime);			
		}
		return null;
	}

	/**
	 * 最終更新日時(java.util.Date)を設定します。
	 * @param lastUpdateTime 最終更新日時
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * 複数名前権限を取得します。
	 * @return 複数権限名前
	 */
	public String[] getRoleNames() {
		return roleNames;
	}

	/**
	 * 複数権限名前を設定します。
	 * @param roleNames 複数権限名前
	 */
	public void setRoleNames(String[] roleNames) {
		this.roleNames = roleNames;
	}

	/**
	 * 確認パスワードを取得します。
	 * @return 確認パスワード
	 */
	public String getRepassword() {
		if(repassword == null) {
			return null;
		}
		return repassword;
	}

	/**
	 * 確認パスワードを設定します。
	 * @param repassword 確認パスワード
	 */
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

}
