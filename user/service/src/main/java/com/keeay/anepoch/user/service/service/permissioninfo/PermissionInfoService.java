package com.keeay.anepoch.user.service.service.permissioninfo;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.model.query.PermissionInfoQuery;
import com.keeay.anepoch.user.service.service.BaseService;

import java.util.List;

/**
 * @author pany
 */
public interface PermissionInfoService extends BaseService<PermissionInfo, Long> {
    /**
     * 通过permissionCodes查询权限信息
     *
     * @param permissionCodes permissionCodes
     * @return permission
     */
    List<PermissionInfo> listByPermissionCodes(List<String> permissionCodes);

    /**
     * 分页查询权限信息
     *
     * @param permissionInfoQuery permissionInfoQuery
     * @return 权限信息
     */
    CommonPage<PermissionInfo> pageList(PermissionInfoQuery permissionInfoQuery, Integer pageNum, Integer pageSize);

    /**
     * 通过code删除权限信息
     *
     * @param permissionCode permissionCode
     * @return success true orElse false
     */
    Boolean deleteByCode(String permissionCode);
}
