package com.keeay.anepoch.user.service.service.menupermissioninfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.service.BaseService;

import java.util.List;

/**
 * @author pany
 */
public interface MenuPermissionInfoService extends BaseService<MenuPermissionInfo, Long> {
    /**
     * 通过菜单codes获取权限code
     *
     * @param menuCodes menuCodes
     * @return 权限信息
     */
    List<MenuPermissionInfo> listByMenuCodes(List<String> menuCodes);

    /**
     * 通过menuCode修改数据
     *
     * @param menuPermissionInfo menuPermissionInfo
     * @return success true orElse false
     */
    Boolean updateByMenuCode(MenuPermissionInfo menuPermissionInfo);

    /**
     * 根据menuCodeList 删除数据
     *
     * @param menuCodeList menuCodeList
     * @return success true orElse false
     */
    Boolean deleteByMenuCodeList(List<String> menuCodeList);
}
