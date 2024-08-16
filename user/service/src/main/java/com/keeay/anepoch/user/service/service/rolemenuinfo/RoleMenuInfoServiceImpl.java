package com.keeay.anepoch.user.service.service.rolemenuinfo;

import com.google.common.collect.Lists;
import com.keeay.anepoch.user.service.dao.rolemenuinfo.RoleMenuInfoMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.keeay.anepoch.user.service.service.BaseServiceImpl;
import com.keeay.anepoch.user.service.model.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author pany
 */
@Service
public class RoleMenuInfoServiceImpl extends BaseServiceImpl<RoleMenuInfo, Long> implements RoleMenuInfoService {
    @Resource
    private RoleMenuInfoMapper roleMenuInfoMapper;

    /**
     * 通过角色codes获取角色拥有的菜单信息
     *
     * @param roleCodes roleCodes
     * @return 角色菜单信息
     */
    @Override
    public List<RoleMenuInfo> listByRoleCodes(List<String> roleCodes) {
        if (CollectionUtils.isEmpty(roleCodes)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return roleMenuInfoMapper.getRoleMenuListByRoleCodes(roleCodes);
    }

    /**
     * 通过roleCode修改数据
     *
     * @param roleMenuInfo roleMenuInfo
     * @return success true orElse false
     */
    @Override
    public Boolean updateByRoleCode(RoleMenuInfo roleMenuInfo) {
        if (StringUtils.isBlank(roleMenuInfo.getRoleCode())) {
            return false;
        }
        return roleMenuInfoMapper.updateByRoleCode(roleMenuInfo);
    }

    /**
     * 根据role code获取数据
     *
     * @param roleCode roleCode
     * @return record
     */
    @Override
    public RoleMenuInfo getOneByRoleCode(String roleCode) {
        if (StringUtils.isBlank(roleCode)) {
            return null;
        }
        return roleMenuInfoMapper.getOneByRoleCode(roleCode);
    }

    /**
     * 通过roleCodeList 删除数据
     *
     * @param roleCodeList roleCodeList
     * @return success true orElse false
     */
    @Override
    public Boolean deleteByRoleCodeList(List<String> roleCodeList) {
        if (CollectionUtils.isEmpty(roleCodeList)) {
            return false;
        }
        return this.roleMenuInfoMapper.deleteByRoleCodeList(roleCodeList) > 0;
    }
}
