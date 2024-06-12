package com.keeay.anepoch.user.service.service.rolemenuinfo;

import com.google.common.collect.Lists;
import com.keeay.anepoch.user.service.dao.rolemenuinfo.RoleMenuInfoMapper;
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
}
