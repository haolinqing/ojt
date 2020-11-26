package jp.co.ntt.ojt.domain.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jp.co.ntt.ojt.domain.model.User;

/**
 * ユーザに関するサービスインタフェース. 
 */
public interface UserService {

	/**
	 * ユーザIDや名前を条件にしたユーザの取得（ページネーション）
	 * 
	 * @param user ユーザ
	 * @param pageable 取得範囲情報
	 * @return 条件と一致するユーザ
	 */
	Page<User> findPageByInfo(User user,Pageable pageable);
	
	/**
	 * ユーザIDを条件にしたユーザの論理削除.
	 * 
	 * @param user ユーザ
	 */
	void delete(User user);
	
	/**
	 * ユーザIDによるユーザ検索.
	 * @param userId ユーザID
	 * @return 条件と一致するユーザ.
	 */
	User findOneByUserId(String userId);
	
	/**
	 * ユーザの更新.
	 * 
	 * @param user ユーザ
	 */
	void update(User user);
	
	/**
	 * ユーザの登録.
	 * 
	 * @param user ユーザ
	 */
	void register(User user);

	/**
	 * ユーザの初期化(パスワード変更).
	 * 
	 * @param user ユーザ
	 */
	void initialization(User user);
	
	/**
	 * userIdが存在するかどうかを判別
	 * 
	 * @param userId ユーザーId
	 * @return userIdが存在した場合にtrue
	 */
	boolean isExistOfUserId(String userId);
	
}
