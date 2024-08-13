package com.keeay.anepoch.user.service.service.permissioninfo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.user.service.dao.permissioninfo.PermissionInfoMapper;
import com.keeay.anepoch.user.service.model.query.PermissionInfoQuery;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 分页查询权限信息
     *
     * @param permissionInfoQuery permissionInfoQuery
     * @return 权限信息
     */
    @Override
    public CommonPage<PermissionInfo> pageList(PermissionInfoQuery permissionInfoQuery, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PermissionInfo> list = this.permissionInfoMapper.pageList(permissionInfoQuery);
        PageInfo<PermissionInfo> pageInfo = new PageInfo<>(list);
        return new CommonPage<>((long) pageInfo.getPageNum(), (long) pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 通过code删除权限信息
     *
     * @param permissionCode permissionCode
     * @return success true orElse false
     */
    @Override
    public Boolean deleteByCode(String permissionCode) {
        if (StringUtils.isBlank(permissionCode)) {
            return false;
        }
        return permissionInfoMapper.deleteByCode(permissionCode) > 0;
    }


}
