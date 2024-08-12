package com.keeay.anepoch.user.service.service.organizationinfo;

import com.google.common.collect.Lists;
import com.keeay.anepoch.user.service.dao.organizationinfo.OrganizationInfoMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.keeay.anepoch.user.service.service.BaseServiceImpl;
import com.keeay.anepoch.user.service.model.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author AI Admin
 */
@Service
public class OrganizationInfoServiceImpl extends BaseServiceImpl<OrganizationInfo, Long> implements OrganizationInfoService {
    @Resource
    private OrganizationInfoMapper organizationInfoMapper;

    /**
     * 根据codeList获取数据
     *
     * @param organizationCodeList organizationCodeList
     * @return data list
     */
    @Override
    public List<OrganizationInfo> getListByCodes(List<String> organizationCodeList) {
        if (CollectionUtils.isEmpty(organizationCodeList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return organizationInfoMapper.getListByCodes(organizationCodeList);
    }
}
