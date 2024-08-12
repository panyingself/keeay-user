
package com.keeay.anepoch.user.biz.userinfo;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
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
     * 修改 record active状态
     *
     * @param editUserInfoBo editUserInfoBo
     * @return success true orElse false
     */
    boolean changeEnable(UserInfoBo editUserInfoBo);

    /**
     * 新增 record
     *
     * @param userCode userCode
     * @return success true orElse false
     */
    boolean removeByUserCode(String userCode);

    /**
     * 查询record集合
     *
     * @param queryUserInfoBo queryUserInfoBo
     * @return record list
     */
    List<UserInfoBo> list(UserInfoBo queryUserInfoBo);

    /**
     * 查询record detail
     *
     * @param recordId recordId
     * @return record detail
     */
    UserInfoBo fetchDetailById(Long recordId);

    /**
     * 分页查询列表
     *
     * @param pageQueryBo pageQueryBo
     * @return page list
     */
    CommonPage<UserInfoBo> pageList(UserInfoBo pageQueryBo);

}

