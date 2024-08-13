package com.keeay.anepoch.user.service.service.menupermissioninfo;

import com.google.common.collect.Lists;
import com.keeay.anepoch.user.service.dao.menupermissioninfo.MenuPermissionInfoMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.keeay.anepoch.user.service.service.BaseServiceImpl;
import com.keeay.anepoch.user.service.model.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pany
 */
@Service
public class MenuPermissionInfoServiceImpl extends BaseServiceImpl<MenuPermissionInfo, Long> implements MenuPermissionInfoService {
    @Resource
    private MenuPermissionInfoMapper menuPermissionInfoMapper;

    /**
     * 通过菜单codes获取权限code
     *
     * @param menuCodes menuCodes
     * @return 权限信息
     */
    @Override
    public List<MenuPermissionInfo> listByMenuCodes(List<String> menuCodes) {
        if (CollectionUtils.isEmpty(menuCodes)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return menuPermissionInfoMapper.getMenuPermissionListByMenuCodes(menuCodes);
    }

    /**
     * 通过menuCode修改数据
     *
     * @param menuPermissionInfo menuPermissionInfo
     * @return success true orElse false
     */
    @Override
    public Boolean updateByMenuCode(MenuPermissionInfo menuPermissionInfo) {
        if(StringUtils.isBlank(menuPermissionInfo.getMenuCode())){
            return false;
        }
        return menuPermissionInfoMapper.updateByMenuCode(menuPermissionInfo) > 0;
    }
}
