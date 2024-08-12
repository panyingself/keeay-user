
package com.keeay.anepoch.user.biz.roleinfo;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.user.biz.userinfo.bo.UserInfoBo;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.roleinfo.bo.*;
import com.keeay.anepoch.user.service.model.query.RoleInfoQuery;
import com.keeay.anepoch.user.service.model.query.UserInfoQuery;
import com.keeay.anepoch.user.service.service.roleinfo.RoleInfoService;
import com.keeay.anepoch.user.biz.roleinfo.converter.RoleInfoBoConverter;
import com.keeay.anepoch.base.commons.monitor.BaseBizTemplate;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author py
 * @date 2019/4
 */
@Service
@Slf4j
public class RoleInfoBizImpl implements RoleInfoBiz {
    @Resource
    private RoleInfoService roleInfoService;

    /**
     * 新增 record
     *
     * @param addRoleInfoBo addRoleInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(RoleInfoBo addRoleInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(addRoleInfoBo), "addRoleInfoBo is null");
            }

            @Override
            protected Boolean process() {
                //新增角色信息
                RoleInfo newRoleInfo = RoleInfoBoConverter.convertToRoleInfo(addRoleInfoBo);
                roleInfoService.insert(newRoleInfo);
                return true;
            }
        }.execute();
    }

    /**
     * 修改 record
     *
     * @param editRoleInfoBo editRoleInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editById(RoleInfoBo editRoleInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editRoleInfoBo), "editRoleInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editRoleInfoBo.getId()), "editRoleInfoBo id is null");
            }

            @Override
            protected Boolean process() {
                RoleInfo oldRoleInfo = roleInfoService.getDetailById(editRoleInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldRoleInfo), "oldRoleInfo is null");
                //修改记录
                RoleInfo waitToUpdate = RoleInfoBoConverter.convertToRoleInfo(editRoleInfoBo);
                roleInfoService.update(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @param queryRoleInfoBo queryRoleInfoBo
     * @return record list
     */
    @Override
    public List<RoleInfoBo> list(RoleInfoBo queryRoleInfoBo) {
        return new BaseBizTemplate<List<RoleInfoBo>>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(queryRoleInfoBo), "queryRoleInfoBo is null");
            }

            @Override
            protected List<RoleInfoBo> process() {
                RoleInfo roleInfoQuery = RoleInfoBoConverter.convertToRoleInfo(queryRoleInfoBo);
                List<RoleInfo> fromDbList = roleInfoService.list(roleInfoQuery);
                if (CollectionUtils.isEmpty(fromDbList)) {
                    return Lists.newArrayList();
                }
                return JsonMoreUtils.ofList(JsonMoreUtils.toJson(fromDbList), RoleInfoBo.class);
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @param queryRoleInfoBo queryRoleInfoBo
     * @return record list
     */
    @Override
    public CommonPage<RoleInfoBo> pageList(RoleInfoBo queryRoleInfoBo) {
        return new BaseBizTemplate<CommonPage<RoleInfoBo>>() {
            @Override
            protected CommonPage<RoleInfoBo> process() {
                RoleInfoQuery roleInfoQuery = JsonMoreUtils.toBean(JsonMoreUtils.toJson(queryRoleInfoBo), RoleInfoQuery.class);
                CommonPage<RoleInfo> pageResult = roleInfoService.pageList(roleInfoQuery, queryRoleInfoBo.getCurrentPage().intValue(), queryRoleInfoBo.getPageSize().intValue());
                List<RoleInfo> dataList = pageResult.getDataList();
                if (CollectionUtils.isEmpty(dataList)) {
                    new CommonPage<>(pageResult.getCurrentPage(), pageResult.getPageSize(), pageResult.getTotalCount(), Lists.newArrayList());
                }
                List<RoleInfoBo> list = JsonMoreUtils.ofList(JsonMoreUtils.toJson(dataList), RoleInfoBo.class);
                return new CommonPage<>(pageResult.getCurrentPage(), pageResult.getPageSize(), pageResult.getTotalCount(), list);
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
    public RoleInfoBo fetchDetailById(Long recordId) {
        return new BaseBizTemplate<RoleInfoBo>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(recordId), "recordId is null");
            }

            @Override
            protected RoleInfoBo process() {
                RoleInfo fromDb = roleInfoService.getDetailById(recordId);
                if (Objects.isNull(fromDb)) {
                    return null;
                }
                return JsonMoreUtils.toBean(JsonMoreUtils.toJson(fromDb), RoleInfoBo.class);
            }
        }.execute();
    }

    /**
     * 根据codeList查询list
     *
     * @param codeList codeList
     * @return list
     */
    @Override
    public List<RoleInfoBo> fetchListByCodeList(List<String> codeList) {
        log.info("fetchListByCodeList biz start, codeList : {}", codeList);
        return new BaseBizTemplate<List<RoleInfoBo>>() {
            @Override
            protected List<RoleInfoBo> process() {
                if (CollectionUtils.isEmpty(codeList)) {
                    return Lists.newArrayListWithCapacity(0);
                }
                //查询role
                List<RoleInfo> listFromDb = roleInfoService.getListByCodeList(codeList);
                if (CollectionUtils.isEmpty(listFromDb)) {
                    return Lists.newArrayListWithCapacity(0);
                }
                return JsonMoreUtils.ofList(JsonMoreUtils.toJson(listFromDb), RoleInfoBo.class);
            }
        }.execute();
    }
}

