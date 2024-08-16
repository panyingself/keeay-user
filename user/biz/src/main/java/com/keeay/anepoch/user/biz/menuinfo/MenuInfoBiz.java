
package com.keeay.anepoch.user.biz.menuinfo;

import com.keeay.anepoch.user.biz.menuinfo.bo.*;

import java.util.List;


/**
 * @author py
 * @date 2019/4
 */
public interface MenuInfoBiz {
    /**
     * 新增 record
     *
     * @param addMenuInfoBo addMenuInfoBo
     * @return success true orElse false
     */
    boolean add(MenuInfoBo addMenuInfoBo);

    /**
     * 修改 record
     *
     * @param editMenuInfoBo editMenuInfoBo
     * @return success true orElse false
     */
    boolean editById(MenuInfoBo editMenuInfoBo);

    /**
     * 通过菜单编码删除菜单
     *
     * @param menuCode menuCode
     * @return success true orElse false
     */
    boolean removeByCode(String menuCode);

    /**
     * 通过菜单编码查询菜单
     *
     * @param menuCode menuCode
     * @return success true orElse false
     */
    MenuInfoBo fetchDetailByCode(String menuCode);

    /**
     * 查询record集合
     *
     * @return record list
     */
    List<MenuInfoBo> fetchTreeList();

    /**
     * 查询record集合
     *
     * @param userCode userCode
     * @return record list
     */
    List<MenuInfoBo> fetchUserTreeList(String userCode);

    /**
     * 获取用户拥有哪些权限code
     *
     * @param userCode userCode
     * @return user menu code list
     */
    List<String> fetchUserHasMenuCodeList(String userCode);
}

