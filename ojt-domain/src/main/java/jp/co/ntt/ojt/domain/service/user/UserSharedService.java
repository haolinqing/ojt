package jp.co.ntt.ojt.domain.service.user;

import jp.co.ntt.ojt.domain.model.User;

/**
 * ユーザに関するサービスインターフェース.
 *
 */
public interface UserSharedService {
	
	/**
	 * ユーザIDによるユーザ検索.
	 * @param userId ユーザID
	 * @return 条件と一致するユーザ.
	 */
	public User findOneByUserId(String userId);

}
