package com.keeay.anepoch.user.service.dao.menupermissioninfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Repository
public interface MenuPermissionInfoMapper extends BaseMapper<MenuPermissionInfo> {
    /**
     * 通过菜单codes获取权限code
     *
     * @param menuCodes menuCodes
     * @return 权限信息
     */
    List<MenuPermissionInfo> getMenuPermissionListByMenuCodes(@Param("menuCodes") List<String> menuCodes);

    /**
     * 通过menuCode修改数据
     *
     * @param menuPermissionInfo menuPermissionInfo
     * @return success true orElse false
     */
    Integer updateByMenuCode(MenuPermissionInfo menuPermissionInfo);

    /**
     * 根据menuCodeList 删除数据
     *
     * @param menuCodeList menuCodeList
     * @return success true orElse false
     */
    Integer deleteByMenuCodeList(@Param("menuCodeList") List<String> menuCodeList);
}
