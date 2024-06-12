package com.keeay.anepoch.user.service.service.permissioninfo;

import com.keeay.anepoch.user.service.model.*;
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
}
