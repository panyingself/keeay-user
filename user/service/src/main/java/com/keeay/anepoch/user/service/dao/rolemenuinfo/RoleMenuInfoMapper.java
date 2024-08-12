package com.keeay.anepoch.user.service.dao.rolemenuinfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Repository
public interface RoleMenuInfoMapper extends BaseMapper<RoleMenuInfo> {
    /**
     * 通过角色codes获取角色拥有的菜单信息
     *
     * @param roleCodes roleCodes
     * @return 角色菜单信息
     */
    List<RoleMenuInfo> getRoleMenuListByRoleCodes(@Param("roleCodes") List<String> roleCodes);

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
    RoleMenuInfo getOneByRoleCode(@Param("roleCode") String roleCode);
}
