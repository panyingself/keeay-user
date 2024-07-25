package com.keeay.anepoch.user.service.service.menuinfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.service.BaseService;

import java.util.List;

/**
 * @author pany
 */
public interface MenuInfoService extends BaseService<MenuInfo, Long> {
    /**
     * 通过menuCodes查询菜单信息
     *
     * @param menuCodes menuCodes menuCodes
     * @return 菜单信息
     */
    List<MenuInfo> listByMenuCodes(List<String> menuCodes);

    /**
     * 通过menuCode删除数据
     *
     * @param menuCode menuCode
     * @return success true orElse false
     */
    Boolean deleteByMenuCode(String menuCode);
}
