package jp.co.ntt.ojt.domain.repository.user;

import java.util.List;
import java.util.TreeSet;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import jp.co.ntt.ojt.domain.model.User;

/**
 * ユーザRepository. 
 *
 */
public interface UserRepository {
	
	/**
	 * 主キーによるユーザ検索.
	 * @param userId ユーザID
	 * @return 条件と一致するユーザ
	 */
	User findOneByUserId(@Param("userId") String userId);

	/**
	 * ユーザIDや名前を条件にしたユーザのページ検索.
	 * 
	 * @param user ユーザ
	 * @param pageable 取得範囲情報
	 * @return 条件と一致するユーザ
	 */
	List<User> findPageByInfo(@Param("user")User user,@Param("pageable") Pageable pageable);

	/**
	 * ユーザIDや名前を条件にしたユーザ数の取得.
	 * 
	 * @param user ユーザ
	 * @return 条件と一致するユーザ数
	 */
	long countByInfo(@Param("user")User user);
	
	/**
	 * ユーザIDを条件にしたユーザの論理削除.
	 * 
	 * @param userId ユーザID
	 * @return 論理削除に成功した場合にtrue
	 */
	boolean delete(@Param("user")User user);
	
	/**
	 * ユーザの更新.
	 * 
	 * @param user ユーザ
	 * @return 更新に成功した場合にtrue
	 */
	boolean update(@Param("user") User user);

	/**
	 * ユーザの登録.
	 * 
	 * @param user ユーザ
	 * @return 登録に成功した場合にtrue
	 */
	boolean insert(@Param("user") User user);

	/**
	 * ユーザパスワードと状態の変更.
	 * 
	 * @param user ユーザ
	 */
	boolean updatePasswordAndStatus(@Param("user") User user);
	
	/**
	 * 全部ユーザーIDの取得.
	 * 
	 * @return 全部ユーザーID
	 */
	TreeSet<Integer> findAllUserId();

}
