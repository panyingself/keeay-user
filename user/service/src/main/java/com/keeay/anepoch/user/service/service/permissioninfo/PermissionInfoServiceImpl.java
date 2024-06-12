package com.keeay.anepoch.user.service.service.permissioninfo;

import com.google.common.collect.Lists;
import com.keeay.anepoch.user.service.dao.permissioninfo.PermissionInfoMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.keeay.anepoch.user.service.service.BaseServiceImpl;
import com.keeay.anepoch.user.service.model.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pany
 */
@Service
public class PermissionInfoServiceImpl extends BaseServiceImpl<PermissionInfo, Long> implements PermissionInfoService {
    @Resource
    private PermissionInfoMapper permissionInfoMapper;

    /**
     * 通过permissionCodes查询权限信息
     *
     * @param permissionCodes permissionCodes
     * @return permission
     */
    @Override
    public List<PermissionInfo> listByPermissionCodes(List<String> permissionCodes) {
        if (CollectionUtils.isEmpty(permissionCodes)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return permissionInfoMapper.listByPermissionCodes(permissionCodes);
    }
}
