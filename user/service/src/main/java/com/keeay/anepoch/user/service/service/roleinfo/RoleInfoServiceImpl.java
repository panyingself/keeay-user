package com.keeay.anepoch.user.service.service.roleinfo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.user.service.dao.roleinfo.RoleInfoMapper;
import com.keeay.anepoch.user.service.model.query.RoleInfoQuery;
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
public class RoleInfoServiceImpl extends BaseServiceImpl<RoleInfo, Long> implements RoleInfoService {
    @Resource
    private RoleInfoMapper roleInfoMapper;

    /**
     * roleInfoQuery
     *
     * @param roleInfoQuery roleInfoQuery
     * @param pageNum       页码
     * @param pageSize      每页显示的条数
     * @return 用户列表
     */
    @Override
    public CommonPage<RoleInfo> pageList(RoleInfoQuery roleInfoQuery, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RoleInfo> list = this.roleInfoMapper.pageList(roleInfoQuery);
        PageInfo<RoleInfo> pageInfo = new PageInfo<>(list);
        return new CommonPage<>((long) pageInfo.getPageNum(), (long) pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据codeList查询list
     *
     * @param codeList codeList
     * @return data list
     */
    @Override
    public List<RoleInfo> getListByCodeList(List<String> codeList) {
        if (CollectionUtils.isEmpty(codeList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return this.roleInfoMapper.getListByCodeList(codeList);
    }

    /**
     * 通过角色code修改数据
     *
     * @param roleInfo roleInfo
     * @return success true orElse false
     */
    @Override
    public Boolean updateByCode(RoleInfo roleInfo) {
        if (StringUtils.isBlank(roleInfo.getRoleCode())) {
            return false;
        }
        return this.roleInfoMapper.updateByCode(roleInfo);
    }

    /**
     * 通过roleCode 删除数据
     *
     * @param roleCodeList roleCodeList
     * @return success true orElse false
     */
    @Override
    public Boolean deleteByRoleCodeList(List<String> roleCodeList) {
        if (CollectionUtils.isEmpty(roleCodeList)) {
            return false;
        }
        return this.roleInfoMapper.deleteByRoleCodeList(roleCodeList) > 0;
    }
}
