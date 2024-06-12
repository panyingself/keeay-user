package com.keeay.anepoch.user.service.dao.permissioninfo;

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
public interface PermissionInfoMapper extends BaseMapper<PermissionInfo>{
    /**
     * 通过permissionCodes查询权限信息
     *
     * @param permissionCodes permissionCodes
     * @return permission
     */
    List<PermissionInfo> listByPermissionCodes(@Param("permissionCodes") List<String> permissionCodes);
}
