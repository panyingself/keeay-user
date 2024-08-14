package com.keeay.anepoch.user.service.service.organizationinfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.service.BaseService;

import java.util.List;

/**
 * @author AI Admin
 */
public interface OrganizationInfoService extends BaseService<OrganizationInfo, Long> {
    /**
     * 根据codeList获取数据
     *
     * @param organizationCodeList organizationCodeList
     * @return data list
     */
    List<OrganizationInfo> getListByCodes(List<String> organizationCodeList);

    /**
     * 通过code删除数据
     *
     * @param code code
     * @return success true orElse false
     */
    Boolean deleteByCode(String code);
}
