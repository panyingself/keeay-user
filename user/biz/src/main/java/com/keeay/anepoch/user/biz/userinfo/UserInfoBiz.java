
package com.keeay.anepoch.user.biz.userinfo;

import com.keeay.anepoch.user.biz.userinfo.bo.*;
import java.util.List;


/**
 * @author py
 * @date 2019/4
 */
public interface UserInfoBiz {
	/**
	 * 新增 record
	 *
	 * @param addUserInfoBo addUserInfoBo
	 * @return success true orElse false
	 */
	boolean add(UserInfoBo addUserInfoBo);

	/**
	 * 修改 record
	 *
	 * @param editUserInfoBo editUserInfoBo
	 * @return success true orElse false
	 */
	boolean editById(UserInfoBo editUserInfoBo);
	/**
	 * 查询record集合
	 * @param queryUserInfoBo queryUserInfoBo
	 * @return record list
	 */
	List<UserInfoBo> list(UserInfoBo queryUserInfoBo);

	/**
	 * 查询record detail
	 * @param recordId recordId
	 * @return record detail
	 */
	UserInfoBo fetchDetailById(Long recordId);
}

