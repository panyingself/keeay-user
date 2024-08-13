
package com.keeay.anepoch.user.biz.menupermissioninfo;

import com.keeay.anepoch.user.biz.menupermissioninfo.bo.*;

import java.util.List;


/**
 * @author py
 * @date 2019/4
 */
public interface MenuPermissionInfoBiz {
    /**
     * 新增 record
     *
     * @param addMenuPermissionInfoBo addMenuPermissionInfoBo
     * @return success true orElse false
     */
    boolean add(MenuPermissionInfoBo addMenuPermissionInfoBo);

    /**
     * 修改 record
     *
     * @param editMenuPermissionInfoBo editMenuPermissionInfoBo
     * @return success true orElse false
     */
    boolean editById(MenuPermissionInfoBo editMenuPermissionInfoBo);

    /**
     * 修改 record
     *
     * @param editMenuPermissionInfoBo editMenuPermissionInfoBo
     * @return success true orElse false
     */
    boolean editByMenuCode(MenuPermissionInfoBo editMenuPermissionInfoBo);

    /**
     * 查询record集合
     *
     * @param queryMenuPermissionInfoBo queryMenuPermissionInfoBo
     * @return record list
     */
    List<MenuPermissionInfoBo> list(MenuPermissionInfoBo queryMenuPermissionInfoBo);

    /**
     * 查询record detail
     *
     * @param recordId recordId
     * @return record detail
     */
    MenuPermissionInfoBo fetchDetailById(Long recordId);

    /**
     * 查询record detail
     *
     * @param menuCode menuCode
     * @return record detail
     */
    MenuPermissionInfoBo fetchDetailByCode(String menuCode);

    /**
     * 根据菜单编码，获取权限code
     *
     * @param menuCodes menuCodes
     * @return permission codes
     */
    List<String> fetchPermissionCodeList(List<String> menuCodes);
}

