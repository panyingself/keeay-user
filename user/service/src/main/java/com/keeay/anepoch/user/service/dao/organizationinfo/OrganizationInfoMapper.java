package com.keeay.anepoch.user.service.dao.organizationinfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author AI Admin
 */
@Repository
public interface OrganizationInfoMapper extends BaseMapper<OrganizationInfo> {
    /**
     * 根据codeList获取数据
     *
     * @param organizationCodeList organizationCodeList
     * @return data list
     */
    List<OrganizationInfo> getListByCodes(@Param("organizationCodeList") List<String> organizationCodeList);

    /**
     * 通过code删除数据
     *
     * @param code code
     * @return success true orElse false
     */
    Integer deleteByCode(@Param("code") String code);
}
