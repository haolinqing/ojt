package jp.co.ntt.ojt.domain.repository.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import jp.co.ntt.ojt.domain.model.Role;

/**
 * ロールRepository. 
 *
 */
public interface RoleRepository {

	/**
	 * ユーザIDを条件にしたロール検索.
	 * @param userId ユーザID
	 * @return 条件と一致するロール
	 */
	List<Role> findByUserId(@Param("userId") String userId);
	
	/**
	 * ロールの作成.
	 * @param userId ユーザID
	 * @param roleName 役割名
	 * @return 作成に成功した場合にtrue
	 */
	boolean insert(@Param("userId") String userId, @Param("roleName") String roleName);
	
	/**
	 * ユーザIDと役割名を条件にしたロールの削除.
	 * @param userId ユーザID
	 * @param roleName 役割名
	 * 削除に成功した場合にtrue
	 */
	boolean delete(@Param("userId") String userId, @Param("roleName") String roleName);

}
