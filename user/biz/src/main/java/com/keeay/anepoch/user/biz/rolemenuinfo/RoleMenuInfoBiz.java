
package com.keeay.anepoch.user.biz.rolemenuinfo;

import com.keeay.anepoch.user.biz.rolemenuinfo.bo.*;

import java.util.List;


/**
 * @author py
 * @date 2019/4
 */
public interface RoleMenuInfoBiz {
    /**
     * 新增 record
     *
     * @param addRoleMenuInfoBo addRoleMenuInfoBo
     * @return success true orElse false
     */
    boolean add(RoleMenuInfoBo addRoleMenuInfoBo);

    /**
     * 修改 record
     *
     * @param editRoleMenuInfoBo editRoleMenuInfoBo
     * @return success true orElse false
     */
    boolean editByRoleCode(RoleMenuInfoBo editRoleMenuInfoBo);

    /**
     * 查询record集合
     *
     * @param queryRoleMenuInfoBo queryRoleMenuInfoBo
     * @return record list
     */
    List<RoleMenuInfoBo> list(RoleMenuInfoBo queryRoleMenuInfoBo);

    /**
     * 查询record detail
     *
     * @param recordId recordId
     * @return record detail
     */
    RoleMenuInfoBo fetchDetailById(Long recordId);

    /**
     * 通过角色id获取菜单信息
     *
     * @param roleCodeList roleCodeList
     * @return 菜单信息集合
     */
    List<String> getMenuCodeListByRoleCodes(List<String> roleCodeList);

    /**
     * 根据角色code获取roleMenuData
     *
     * @param roleCodeList roleCodeList
     * @return list
     */
    List<RoleMenuInfoBo> getRoleMenuListByRoleCodes(List<String> roleCodeList);
}

