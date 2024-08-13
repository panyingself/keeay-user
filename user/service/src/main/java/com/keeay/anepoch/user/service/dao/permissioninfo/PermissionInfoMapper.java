package com.keeay.anepoch.user.service.dao.permissioninfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.dao.BaseMapper;
import com.keeay.anepoch.user.service.model.query.PermissionInfoQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Repository
public interface PermissionInfoMapper extends BaseMapper<PermissionInfo> {
    /**
     * 通过permissionCodes查询权限信息
     *
     * @param permissionCodes permissionCodes
     * @return permission
     */
    List<PermissionInfo> listByPermissionCodes(@Param("permissionCodes") List<String> permissionCodes);

    /**
     * 分页查询权限信息
     *
     * @param permissionInfoQuery permissionInfoQuery
     * @return permission
     */
    List<PermissionInfo> pageList(PermissionInfoQuery permissionInfoQuery);

    /**
     * 通过code删除权限信息
     *
     * @param permissionCode permissionCode
     * @return success true orElse false
     */
    Integer deleteByCode(@Param("permissionCode") String permissionCode);
}
