package com.keeay.anepoch.user.service.service.menuinfo;

import com.google.common.collect.Lists;
import com.keeay.anepoch.user.service.dao.menuinfo.MenuInfoMapper;
import com.keeay.anepoch.user.service.model.MenuInfo;
import com.keeay.anepoch.user.service.service.BaseServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pany
 */
@Service
public class MenuInfoServiceImpl extends BaseServiceImpl<MenuInfo, Long> implements MenuInfoService {
    @Resource
    private MenuInfoMapper menuInfoMapper;

    /**
     * 通过menuCodes查询菜单信息
     *
     * @param menuCodes menuCodes menuCodes
     * @return 菜单信息
     */
    @Override
    public List<MenuInfo> listByMenuCodes(List<String> menuCodes) {
        if(CollectionUtils.isEmpty(menuCodes)){
            return Lists.newArrayListWithCapacity(0);
        }
        return menuInfoMapper.getMenuListByMenuCodes(menuCodes);
    }
}
