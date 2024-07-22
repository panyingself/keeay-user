
package com.keeay.anepoch.user.biz.menuinfo;

import com.keeay.anepoch.base.commons.monitor.BaseBizTemplate;
import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.keeay.anepoch.user.biz.menuinfo.bo.*;
import com.keeay.anepoch.user.biz.rolemenuinfo.RoleMenuInfoBiz;
import com.keeay.anepoch.user.biz.userroleinfo.UserRoleInfoBiz;
import com.keeay.anepoch.user.service.model.MenuInfo;
import com.keeay.anepoch.user.service.service.menuinfo.MenuInfoService;
import com.keeay.anepoch.user.biz.menuinfo.converter.MenuInfoBoConverter;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author py
 * @date 2019/4
 */
@Service
@Slf4j
public class MenuInfoBizImpl implements MenuInfoBiz {
    private static final String MENU_TREE_HEAD_NODE_FLAG = "-1";
    @Resource
    private MenuInfoService menuInfoService;
    @Resource
    private RoleMenuInfoBiz roleMenuInfoBiz;
    @Resource
    private UserRoleInfoBiz userRoleInfoBiz;

    /**
     * 新增 record
     *
     * @param addMenuInfoBo addMenuInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(MenuInfoBo addMenuInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(addMenuInfoBo), "addMenuInfoBo is null");
            }

            @Override
            protected Boolean process() {
                //新增角色信息
                MenuInfo newMenuInfo = MenuInfoBoConverter.convertToMenuInfo(addMenuInfoBo);
                menuInfoService.insert(newMenuInfo);
                return true;
            }
        }.execute();
    }

    /**
     * 修改 record
     *
     * @param editMenuInfoBo editMenuInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editById(MenuInfoBo editMenuInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editMenuInfoBo), "editMenuInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editMenuInfoBo.getId()), "editMenuInfoBo id is null");
            }

            @Override
            protected Boolean process() {
                MenuInfo oldMenuInfo = menuInfoService.getDetailById(editMenuInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldMenuInfo), "oldMenuInfo is null");
                //修改记录
                MenuInfo waitToUpdate = MenuInfoBoConverter.convertToMenuInfo(editMenuInfoBo);
                menuInfoService.update(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @return record list
     */
    @Override
    public List<MenuInfoBo> getTreeList() {
        return new BaseBizTemplate<List<MenuInfoBo>>() {
            @Override
            protected List<MenuInfoBo> process() {
                //todo 内存分页，一次性查询
                //可优化点: 缓存
                List<MenuInfo> fromDbList = menuInfoService.list(new MenuInfo());
                if (CollectionUtils.isEmpty(fromDbList)) {
                    return Lists.newArrayList();
                }
                //树化返回
                List<MenuInfo> sortedList = fromDbList.stream().sorted(Comparator.comparing(MenuInfo::getSort)).collect(Collectors.toList());
                return getTreeList(sortedList);
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @return record list
     */
    @Override
    public List<MenuInfoBo> getUserTreeList(String userCode) {
        log.info("getUserTreeList biz start, userCode : {}", userCode);
        return new BaseBizTemplate<List<MenuInfoBo>>() {
            @Override
            protected List<MenuInfoBo> process() {
                List<MenuInfo> menuListFromDb = getUserMenuList(userCode);
                //树化返回
                return getTreeList(menuListFromDb);
            }
        }.execute();
    }

    /**
     * 获取用户拥有哪些权限code
     *
     * @param userCode userCode
     * @return user menu code list
     */
    @Override
    public List<String> getUserHasMenuCodeList(String userCode) {
        log.info("getUserHasMenuCodeList biz start, userCode : {}", userCode);
        return new BaseBizTemplate<List<String>>() {
            @Override
            protected List<String> process() {
                List<MenuInfo> menuListFromDb = getUserMenuList(userCode);
                return menuListFromDb.stream()
                        .map(MenuInfo::getMenuCode)
                        .collect(Collectors.toList());
            }
        }.execute();
    }

    private List<MenuInfo> getUserMenuList(String userCode) {
        //根据用户编码查询拥有的角色信息
        List<String> roleCodeList = userRoleInfoBiz.getRoleListByUserCode(userCode);
        ConditionUtils.checkArgument(CollectionUtils.isNotEmpty(roleCodeList), "用户没有拥有角色信息");
        //获取角色所拥有的权限菜单信息
        List<String> hasMenuCodeList = roleMenuInfoBiz.getMenuCodeListByRoleCodes(roleCodeList);
        ConditionUtils.checkArgument(CollectionUtils.isNotEmpty(hasMenuCodeList), "用户没有拥有菜单信息");
        //查询拥有的菜单信息
        List<MenuInfo> menuListFromDb = menuInfoService.listByMenuCodes(hasMenuCodeList);
        ConditionUtils.checkArgument(CollectionUtils.isNotEmpty(menuListFromDb), "用户没有拥有菜单信息");
        return menuListFromDb;
    }

    private List<MenuInfoBo> getTreeList(List<MenuInfo> targetList) {
        //分组
        Map<String, List<MenuInfoBo>> parentGroupMap = targetList.stream()
                .map(data -> JsonMoreUtils.toBean(JsonMoreUtils.toJson(data), MenuInfoBo.class))
                .collect(Collectors.groupingBy(MenuInfoBo::getParentMenuCode));
        //获取头/根结点
        List<MenuInfoBo> rootNodeList = parentGroupMap.get(MENU_TREE_HEAD_NODE_FLAG);
        if (CollectionUtils.isEmpty(rootNodeList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        //递归fill菜单数据
        fillChildren(rootNodeList, parentGroupMap);
        return rootNodeList;
    }

    public void fillChildren(List<MenuInfoBo> currentMenuList, Map<String, List<MenuInfoBo>> parentGroupMap) {
        //寻找children
        for (MenuInfoBo currentMenu : currentMenuList) {
            List<MenuInfoBo> children = parentGroupMap.get(currentMenu.getMenuCode());
            if (CollectionUtils.isEmpty(children)) {
                continue;
            }
            currentMenu.setChildren(children);
            //fill children
            this.fillChildren(children, parentGroupMap);
        }
    }


}

