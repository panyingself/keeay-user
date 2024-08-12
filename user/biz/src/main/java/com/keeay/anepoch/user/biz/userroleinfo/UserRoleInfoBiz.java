
package com.keeay.anepoch.user.biz.userroleinfo;

import com.keeay.anepoch.user.biz.userroleinfo.bo.*;

import java.util.List;


/**
 * @author py
 * @date 2019/4
 */
public interface UserRoleInfoBiz {
    /**
     * save record 有记录就修改，没记录就新增。
     *
     * @param addUserRoleInfoBo addUserRoleInfoBo
     * @return success true orElse false
     */
    boolean saveByUserCode(UserRoleInfoBo addUserRoleInfoBo);
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
     * 修改 record
     *
     * @param editUserRoleInfoBo editUserRoleInfoBo
     * @return success true orElse false
     */
    boolean editByUserCode(UserRoleInfoBo editUserRoleInfoBo);

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

    /**
     * 通过userCodes查询用户角色关联信息
     *
     * @param userCodes userCodes
     * @return list
     */
    List<UserRoleInfoBo> fetchListByUserCodes(List<String> userCodes);
}

