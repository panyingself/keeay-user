
package com.keeay.anepoch.user.biz.userroleinfo;

import com.keeay.anepoch.user.biz.userroleinfo.bo.*;

import java.util.List;


/**
 * @author py
 * @date 2019/4
 */
public interface UserRoleInfoBiz {
    /**
     * 新增 record
     *
     * @param addUserRoleInfoBo addUserRoleInfoBo
     * @return success true orElse false
     */
    boolean add(UserRoleInfoBo addUserRoleInfoBo);

    /**
     * 修改 record
     *
     * @param editUserRoleInfoBo editUserRoleInfoBo
     * @return success true orElse false
     */
    boolean editById(UserRoleInfoBo editUserRoleInfoBo);

    /**
     * 查询record集合
     *
     * @param queryUserRoleInfoBo queryUserRoleInfoBo
     * @return record list
     */
    List<UserRoleInfoBo> list(UserRoleInfoBo queryUserRoleInfoBo);

    /**
     * 查询record detail
     *
     * @param recordId recordId
     * @return record detail
     */
    UserRoleInfoBo fetchDetailById(Long recordId);

    /**
     * 通过用户编码获取用户拥有的角色信息
     *
     * @param userCode userCode
     * @return role list
     */
    List<String> getRoleListByUserCode(String userCode);
}

