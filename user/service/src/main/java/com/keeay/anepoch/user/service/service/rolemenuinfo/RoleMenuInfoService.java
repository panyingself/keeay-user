package com.keeay.anepoch.user.service.service.rolemenuinfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.service.BaseService;

import java.util.List;

/**
 * @author pany
 */
public interface RoleMenuInfoService extends BaseService<RoleMenuInfo, Long> {
    /**
     * 通过角色codes获取角色拥有的菜单信息
     *
     * @param roleCodes roleCodes
     * @return 角色菜单信息
     */
    List<RoleMenuInfo> listByRoleCodes(List<String> roleCodes);

    /**
     * 通过roleCode修改数据
     *
     * @param roleMenuInfo roleMenuInfo
     * @return success true orElse false
     */
    Boolean updateByRoleCode(RoleMenuInfo roleMenuInfo);

    /**
     * 根据role code获取数据
     *
     * @param roleCode roleCode
     * @return record
     */
    RoleMenuInfo getOneByRoleCode(String roleCode);
}
