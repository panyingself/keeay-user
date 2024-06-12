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
}
