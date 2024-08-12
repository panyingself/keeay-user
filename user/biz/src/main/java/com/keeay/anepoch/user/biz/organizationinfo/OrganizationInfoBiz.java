
package com.keeay.anepoch.user.biz.organizationinfo;

import com.keeay.anepoch.user.biz.organizationinfo.bo.*;

import java.util.List;


/**
 * @author AI Admin
 */
public interface OrganizationInfoBiz {
    /**
     * 新增 record
     *
     * @param addOrganizationInfoBo addOrganizationInfoBo
     * @return success true orElse false
     */
    boolean add(OrganizationInfoBo addOrganizationInfoBo);

    /**
     * 修改 record
     *
     * @param editOrganizationInfoBo editOrganizationInfoBo
     * @return success true orElse false
     */
    boolean editById(OrganizationInfoBo editOrganizationInfoBo);

    /**
     * 查询record集合
     *
     * @param queryOrganizationInfoBo queryOrganizationInfoBo
     * @return record list
     */
    List<OrganizationInfoBo> getTreeList(OrganizationInfoBo queryOrganizationInfoBo);

    /**
     * 查询record detail
     *
     * @param recordId recordId
     * @return record detail
     */
    OrganizationInfoBo fetchDetailById(Long recordId);

    /**
     * 通过codes 获取 list
     *
     * @param organizationCodeList organizationCodeList
     * @return data list
     */
    List<OrganizationInfoBo> fetchListByCodes(List<String> organizationCodeList);
}

