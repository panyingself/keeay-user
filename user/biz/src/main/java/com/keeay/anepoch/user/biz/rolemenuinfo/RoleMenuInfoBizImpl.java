
package com.keeay.anepoch.user.biz.rolemenuinfo;

import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.base.commons.utils.SplitterUtils;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.rolemenuinfo.bo.*;
import com.keeay.anepoch.user.service.service.rolemenuinfo.RoleMenuInfoService;
import com.keeay.anepoch.user.biz.rolemenuinfo.converter.RoleMenuInfoBoConverter;
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
public class RoleMenuInfoBizImpl implements RoleMenuInfoBiz {
    @Resource
    private RoleMenuInfoService roleMenuInfoService;

    /**
     * 新增 record
     *
     * @param addRoleMenuInfoBo addRoleMenuInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(RoleMenuInfoBo addRoleMenuInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(addRoleMenuInfoBo), "addRoleMenuInfoBo is null");
            }

            @Override
            protected Boolean process() {
                //新增角色信息
                RoleMenuInfo newRoleMenuInfo = RoleMenuInfoBoConverter.convertToRoleMenuInfo(addRoleMenuInfoBo);
                roleMenuInfoService.insert(newRoleMenuInfo);
                return true;
            }
        }.execute();
    }

    /**
     * 修改 record
     *
     * @param editRoleMenuInfoBo editRoleMenuInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editById(RoleMenuInfoBo editRoleMenuInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editRoleMenuInfoBo), "editRoleMenuInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editRoleMenuInfoBo.getId()), "editRoleMenuInfoBo id is null");
            }

            @Override
            protected Boolean process() {
                RoleMenuInfo oldRoleMenuInfo = roleMenuInfoService.getDetailById(editRoleMenuInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldRoleMenuInfo), "oldRoleMenuInfo is null");
                //修改记录
                RoleMenuInfo waitToUpdate = RoleMenuInfoBoConverter.convertToRoleMenuInfo(editRoleMenuInfoBo);
                roleMenuInfoService.update(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @param queryRoleMenuInfoBo queryRoleMenuInfoBo
     * @return record list
     */
    @Override
    public List<RoleMenuInfoBo> list(RoleMenuInfoBo queryRoleMenuInfoBo) {
        return new BaseBizTemplate<List<RoleMenuInfoBo>>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(queryRoleMenuInfoBo), "queryRoleMenuInfoBo is null");
            }

            @Override
            protected List<RoleMenuInfoBo> process() {
                RoleMenuInfo roleMenuInfoQuery = RoleMenuInfoBoConverter.convertToRoleMenuInfo(queryRoleMenuInfoBo);
                List<RoleMenuInfo> fromDbList = roleMenuInfoService.list(roleMenuInfoQuery);
                if (CollectionUtils.isEmpty(fromDbList)) {
                    return Lists.newArrayList();
                }
                return JsonMoreUtils.ofList(JsonMoreUtils.toJson(fromDbList), RoleMenuInfoBo.class);
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
    public RoleMenuInfoBo fetchDetailById(Long recordId) {
        return new BaseBizTemplate<RoleMenuInfoBo>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(recordId), "recordId is null");
            }

            @Override
            protected RoleMenuInfoBo process() {
                RoleMenuInfo fromDb = roleMenuInfoService.getDetailById(recordId);
                if (Objects.isNull(fromDb)) {
                    return null;
                }
                return JsonMoreUtils.toBean(JsonMoreUtils.toJson(fromDb), RoleMenuInfoBo.class);
            }
        }.execute();
    }

    /**
     * 通过角色id获取菜单信息
     *
     * @param roleCodeList roleCodeList
     * @return 菜单信息集合
     */
    @Override
    public List<String> getMenuCodeListByRoleCodes(List<String> roleCodeList) {
        log.info("getMenuCodeListByRoleCodes biz start, roleCodeList : {}", JsonMoreUtils.toJson(roleCodeList));
        return new BaseBizTemplate<List<String>>() {
            @Override
            protected List<String> process() {
                if (CollectionUtils.isEmpty(roleCodeList)) {
                    log.info("getMenuCodeListByRoleCodes biz fast end, roleCodeList is empty");
                    return Lists.newArrayListWithCapacity(0);
                }
                List<RoleMenuInfo> listFromDb = roleMenuInfoService.listByRoleCodes(roleCodeList);
                if (CollectionUtils.isEmpty(listFromDb)) {
                    log.info("getMenuCodeListByRoleCodes biz fast end, listFromDb is empty");
                    return Lists.newArrayListWithCapacity(0);
                }
                return listFromDb.stream()
                        .filter(data -> StringUtils.isNotBlank(data.getMenuCodes()))
                        .map(data -> SplitterUtils.SPLITTER_COMMA.splitToList(data.getMenuCodes()))
                        .flatMap(Collection::stream).distinct().collect(Collectors.toList());
            }
        }.execute();
    }
}

