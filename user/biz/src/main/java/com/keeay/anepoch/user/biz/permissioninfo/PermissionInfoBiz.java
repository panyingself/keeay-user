
package com.keeay.anepoch.user.biz.permissioninfo;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.permissioninfo.bo.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author py
 * @date 2019/4
 */
public interface PermissionInfoBiz {
    /**
     * 新增 record
     *
     * @param addPermissionInfoBo addPermissionInfoBo
     * @return success true orElse false
     */
    boolean add(PermissionInfoBo addPermissionInfoBo);

    /**
     * 修改 record
     *
     * @param editPermissionInfoBo editPermissionInfoBo
     * @return success true orElse false
     */
    boolean editById(PermissionInfoBo editPermissionInfoBo);

    /**
     * 查询record集合
     *
     * @param queryPermissionInfoBo queryPermissionInfoBo
     * @return record list
     */
    List<PermissionInfoBo> list(PermissionInfoBo queryPermissionInfoBo);

    /**
     * 查询record集合
     *
     * @param queryPermissionInfoBo queryPermissionInfoBo
     * @return record list
     */
    CommonPage<PermissionInfoBo> pageList(PermissionInfoBo queryPermissionInfoBo);

    /**
     * 查询record detail
     *
     * @param recordId recordId
     * @return record detail
     */
    PermissionInfoBo fetchDetailById(Long recordId);

    /**
     * 获取用户拥有的权限信息
     *
     * @param userCode userCode
     * @return permissions
     */
    List<String> getUserPermissions(String userCode);

    /**
     * 验证用户是否拥有servlet权限
     *
     * @param userCode    userCode
     * @param servletPath servletPath
     * @return 有 success 没有 false
     */
    Boolean checkUserServletPermission(String userCode, String servletPath);

    /**
     * 根据code删除权限信息
     *
     * @param permissionCode permissionCode
     * @return success true orElse false
     */
    Boolean deleteByCode(String permissionCode);
}

