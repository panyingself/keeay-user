
package com.keeay.anepoch.user.biz.menupermissioninfo;

import com.keeay.anepoch.base.commons.lang.Safes;
import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.base.commons.utils.SplitterUtils;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.menupermissioninfo.bo.*;
import com.keeay.anepoch.user.service.service.menupermissioninfo.MenuPermissionInfoService;
import com.keeay.anepoch.user.biz.menupermissioninfo.converter.MenuPermissionInfoBoConverter;
import com.keeay.anepoch.base.commons.monitor.BaseBizTemplate;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author py
 * @date 2019/4
 */
@Service
@Slf4j
public class MenuPermissionInfoBizImpl implements MenuPermissionInfoBiz {
    @Resource
    private MenuPermissionInfoService menuPermissionInfoService;

    /**
     * 新增 record
     *
     * @param addMenuPermissionInfoBo addMenuPermissionInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(MenuPermissionInfoBo addMenuPermissionInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(addMenuPermissionInfoBo), "addMenuPermissionInfoBo is null");
            }

            @Override
            protected Boolean process() {
                //新增角色信息
                MenuPermissionInfo newMenuPermissionInfo = MenuPermissionInfoBoConverter.convertToMenuPermissionInfo(addMenuPermissionInfoBo);
                menuPermissionInfoService.insert(newMenuPermissionInfo);
                return true;
            }
        }.execute();
    }

    /**
     * 修改 record
     *
     * @param editMenuPermissionInfoBo editMenuPermissionInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editById(MenuPermissionInfoBo editMenuPermissionInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editMenuPermissionInfoBo), "editMenuPermissionInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editMenuPermissionInfoBo.getId()), "editMenuPermissionInfoBo id is null");
            }

            @Override
            protected Boolean process() {
                MenuPermissionInfo oldMenuPermissionInfo = menuPermissionInfoService.getDetailById(editMenuPermissionInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldMenuPermissionInfo), "oldMenuPermissionInfo is null");
                //修改记录
                MenuPermissionInfo waitToUpdate = MenuPermissionInfoBoConverter.convertToMenuPermissionInfo(editMenuPermissionInfoBo);
                menuPermissionInfoService.update(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 修改 record
     *
     * @param editMenuPermissionInfoBo editMenuPermissionInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editByMenuCode(MenuPermissionInfoBo editMenuPermissionInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editMenuPermissionInfoBo), "editMenuPermissionInfoBo is null");
                ConditionUtils.checkArgument(StringUtils.isNotBlank(editMenuPermissionInfoBo.getMenuCode()), "editMenuPermissionInfoBo menuCode is null");
            }

            @Override
            protected Boolean process() {
                MenuPermissionInfo query = new MenuPermissionInfo();
                query.setMenuCode(editMenuPermissionInfoBo.getMenuCode());
                List<MenuPermissionInfo> oldMenuPermissionInfoList = menuPermissionInfoService.list(query);
                ConditionUtils.checkArgument(CollectionUtils.isNotEmpty(oldMenuPermissionInfoList), "oldMenuPermissionInfo is null");
                //修改记录
                MenuPermissionInfo waitToUpdate = MenuPermissionInfoBoConverter.convertToMenuPermissionInfo(editMenuPermissionInfoBo);
                menuPermissionInfoService.updateByMenuCode(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @param queryMenuPermissionInfoBo queryMenuPermissionInfoBo
     * @return record list
     */
    @Override
    public List<MenuPermissionInfoBo> list(MenuPermissionInfoBo queryMenuPermissionInfoBo) {
        return new BaseBizTemplate<List<MenuPermissionInfoBo>>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(queryMenuPermissionInfoBo), "queryMenuPermissionInfoBo is null");
            }

            @Override
            protected List<MenuPermissionInfoBo> process() {
                MenuPermissionInfo menuPermissionInfoQuery = MenuPermissionInfoBoConverter.convertToMenuPermissionInfo(queryMenuPermissionInfoBo);
                List<MenuPermissionInfo> fromDbList = menuPermissionInfoService.list(menuPermissionInfoQuery);
                if (CollectionUtils.isEmpty(fromDbList)) {
                    return Lists.newArrayList();
                }
                return JsonMoreUtils.ofList(JsonMoreUtils.toJson(fromDbList), MenuPermissionInfoBo.class);
            }
        }.execute();
    }

    /**
     * 查询record
     *
     * @param recordId recordId
     * @return record orElse null
     */
    @Override
    public MenuPermissionInfoBo fetchDetailById(Long recordId) {
        return new BaseBizTemplate<MenuPermissionInfoBo>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(recordId), "recordId is null");
            }

            @Override
            protected MenuPermissionInfoBo process() {
                MenuPermissionInfo fromDb = menuPermissionInfoService.getDetailById(recordId);
                if (Objects.isNull(fromDb)) {
                    return null;
                }
                return JsonMoreUtils.toBean(JsonMoreUtils.toJson(fromDb), MenuPermissionInfoBo.class);
            }
        }.execute();
    }

    /**
     * 查询record
     *
     * @param menuCode menuCode
     * @return record orElse null
     */
    @Override
    public MenuPermissionInfoBo fetchDetailByCode(String menuCode) {
        return new BaseBizTemplate<MenuPermissionInfoBo>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(menuCode), "menuCode is null");
            }

            @Override
            protected MenuPermissionInfoBo process() {
                MenuPermissionInfo query = new MenuPermissionInfo();
                query.setMenuCode(menuCode);
                List<MenuPermissionInfo> oldMenuPermissionInfoList = menuPermissionInfoService.list(query);
                if(CollectionUtils.isEmpty(oldMenuPermissionInfoList)){
                    return null;
                }
                return JsonMoreUtils.toBean(JsonMoreUtils.toJson(oldMenuPermissionInfoList.get(0)), MenuPermissionInfoBo.class);
            }
        }.execute();
    }

    /**
     * 根据菜单编码，获取权限code
     *
     * @param menuCodes menuCodes
     * @return permission codes
     */
    @Override
    public List<String> fetchPermissionCodeList(List<String> menuCodes) {
        log.info("getPermissionCodeList biz start, menuCodes : {}", JsonMoreUtils.toJson(menuCodes));
        return new BaseBizTemplate<List<String>>() {
            @Override
            protected List<String> process() {
                List<MenuPermissionInfo> listFromDb = menuPermissionInfoService.listByMenuCodes(menuCodes);

                return Safes.of(listFromDb).stream()
                        .map(data -> SplitterUtils.SPLITTER_COMMA.splitToList(data.getPermissionCodeList()))
                        .flatMap(Collection::stream)
                        .filter(StringUtils::isNotBlank)
                        .collect(Collectors.toList());
            }
        }.execute();
    }
}

