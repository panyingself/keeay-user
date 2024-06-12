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
}
