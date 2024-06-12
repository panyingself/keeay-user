package com.keeay.anepoch.user.service.dao.menuinfo;

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
public interface MenuInfoMapper extends BaseMapper<MenuInfo>{
    /**
     * 通过menuCodes查询菜单信息
     *
     * @param menuCodes menuCodes menuCodes
     * @return 菜单信息
     */
    List<MenuInfo> getMenuListByMenuCodes(@Param("menuCodes") List<String> menuCodes);
}
