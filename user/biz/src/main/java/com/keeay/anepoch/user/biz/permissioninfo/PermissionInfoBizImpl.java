
package com.keeay.anepoch.user.biz.permissioninfo;

import com.keeay.anepoch.auth.api.context.UserContext;
import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.base.commons.lang.Safes;
import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.user.biz.menuinfo.MenuInfoBiz;
import com.keeay.anepoch.user.biz.menupermissioninfo.MenuPermissionInfoBiz;
import com.keeay.anepoch.user.biz.permissioninfo.helper.PermissionHelper;
import com.keeay.anepoch.user.biz.roleinfo.bo.RoleInfoBo;
import com.keeay.anepoch.user.biz.userroleinfo.UserRoleInfoBiz;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.permissioninfo.bo.*;
import com.keeay.anepoch.user.service.model.query.PermissionInfoQuery;
import com.keeay.anepoch.user.service.model.query.RoleInfoQuery;
import com.keeay.anepoch.user.service.service.permissioninfo.PermissionInfoService;
import com.keeay.anepoch.user.biz.permissioninfo.converter.PermissionInfoBoConverter;
import com.keeay.anepoch.base.commons.monitor.BaseBizTemplate;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author py
 * @date 2019/4
 */
@Service
@Slf4j
public class PermissionInfoBizImpl implements PermissionInfoBiz {
    private static final String SUPER_MANAGER_ROLE_CODE = "1001";
    @Resource
    private PermissionInfoService permissionInfoService;
    @Resource
    private MenuInfoBiz menuInfoBiz;
    @Resource
    private MenuPermissionInfoBiz menuPermissionInfoBiz;
    @Resource
    private UserRoleInfoBiz userRoleInfoBiz;

    /**
     * 新增 record
     *
     * @param addPermissionInfoBo addPermissionInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(PermissionInfoBo addPermissionInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(addPermissionInfoBo), "addPermissionInfoBo is null");
            }

            @Override
            protected Boolean process() {
                //新增角色信息
                PermissionInfo newPermissionInfo = PermissionInfoBoConverter.convertToPermissionInfo(addPermissionInfoBo);
                //设置权限编码
                newPermissionInfo.setPermissionCode(PermissionHelper.generatePermissionCode());
                newPermissionInfo.setCreateUser(UserContext.getUser().getUserName());
                permissionInfoService.insert(newPermissionInfo);
                return true;
            }
        }.execute();
    }

    /**
     * 修改 record
     *
     * @param editPermissionInfoBo editPermissionInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editById(PermissionInfoBo editPermissionInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editPermissionInfoBo), "editPermissionInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editPermissionInfoBo.getId()), "editPermissionInfoBo id is null");
            }

            @Override
            protected Boolean process() {
                PermissionInfo oldPermissionInfo = permissionInfoService.getDetailById(editPermissionInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldPermissionInfo), "oldPermissionInfo is null");
                //修改记录
                PermissionInfo waitToUpdate = PermissionInfoBoConverter.convertToPermissionInfo(editPermissionInfoBo);
                waitToUpdate.setUpdateUser(UserContext.getUser().getUserName());
                permissionInfoService.update(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @param queryPermissionInfoBo queryPermissionInfoBo
     * @return record list
     */
    @Override
    public List<PermissionInfoBo> list(PermissionInfoBo queryPermissionInfoBo) {
        return new BaseBizTemplate<List<PermissionInfoBo>>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(queryPermissionInfoBo), "queryPermissionInfoBo is null");
            }

            @Override
            protected List<PermissionInfoBo> process() {
                PermissionInfo permissionInfoQuery = PermissionInfoBoConverter.convertToPermissionInfo(queryPermissionInfoBo);
                List<PermissionInfo> fromDbList = permissionInfoService.list(permissionInfoQuery);
                if (CollectionUtils.isEmpty(fromDbList)) {
                    return Lists.newArrayList();
                }
                return JsonMoreUtils.ofList(JsonMoreUtils.toJson(fromDbList), PermissionInfoBo.class);
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @param queryPermissionInfoBo queryPermissionInfoBo
     * @return record list
     */
    @Override
    public CommonPage<PermissionInfoBo> pageList(PermissionInfoBo queryPermissionInfoBo) {
        return new BaseBizTemplate<CommonPage<PermissionInfoBo>>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(queryPermissionInfoBo), "queryPermissionInfoBo is null");
            }

            @Override
            protected CommonPage<PermissionInfoBo> process() {
                PermissionInfoQuery permissionInfoQuery = JsonMoreUtils.toBean(JsonMoreUtils.toJson(queryPermissionInfoBo), PermissionInfoQuery.class);
                CommonPage<PermissionInfo> pageResult = permissionInfoService.pageList(permissionInfoQuery, queryPermissionInfoBo.getCurrentPage().intValue(), queryPermissionInfoBo.getPageSize().intValue());
                List<PermissionInfo> dataList = pageResult.getDataList();
                if (CollectionUtils.isEmpty(dataList)) {
                    new CommonPage<>(pageResult.getCurrentPage(), pageResult.getPageSize(), pageResult.getTotalCount(), Lists.newArrayList());
                }
                List<PermissionInfoBo> list = JsonMoreUtils.ofList(JsonMoreUtils.toJson(dataList), PermissionInfoBo.class);
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
    public PermissionInfoBo fetchDetailById(Long recordId) {
        return new BaseBizTemplate<PermissionInfoBo>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(recordId), "recordId is null");
            }

            @Override
            protected PermissionInfoBo process() {
                PermissionInfo fromDb = permissionInfoService.getDetailById(recordId);
                if (Objects.isNull(fromDb)) {
                    return null;
                }
                return JsonMoreUtils.toBean(JsonMoreUtils.toJson(fromDb), PermissionInfoBo.class);
            }
        }.execute();
    }

    /**
     * 获取用户拥有的权限信息
     *
     * @param userCode userCode
     * @return permissions
     */
    @Override
    public List<String> getUserPermissions(String userCode) {
        log.info("getUserPermissions biz start, userCode : {}", userCode);
        return new BaseBizTemplate<List<String>>() {
            @Override
            protected List<String> process() {
                //check用户是否存在
                //查询用户菜单权限
                List<String> userMenuCodeList = menuInfoBiz.fetchUserHasMenuCodeList(userCode);
                if (CollectionUtils.isEmpty(userMenuCodeList)) {
                    log.warn("getUserPermissions biz fast end, userMenuCodeList is empty");
                    return Lists.newArrayListWithCapacity(0);
                }
                //通过菜单权限查询接口权限
                List<String> userPermissionCodeList = menuPermissionInfoBiz.fetchPermissionCodeList(userMenuCodeList);
                if (CollectionUtils.isEmpty(userPermissionCodeList)) {
                    log.warn("getUserPermissions biz fast end, userPermissionCodeList is empty");
                    return Lists.newArrayListWithCapacity(0);
                }
                List<PermissionInfo> permissionInfoList = permissionInfoService.listByPermissionCodes(userPermissionCodeList);
                return Safes.of(permissionInfoList).stream()
                        .map(PermissionInfo::getUri)
                        .collect(Collectors.toList());
            }
        }.execute();
    }

    /**
     * 验证用户是否拥有servlet权限
     *
     * @param userCode    userCode
     * @param servletPath servletPath
     * @return 有 success 没有 false
     */
    @Override
    public Boolean checkUserServletPermission(String userCode, String servletPath) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected Boolean process() {
                ConditionUtils.checkArgument(StringUtils.isNotBlank(userCode), "userCode is blank");
                ConditionUtils.checkArgument(StringUtils.isNotBlank(servletPath), "servletPath is blank");
                //查询用户拥有的角色信息 - 判断是否有超级管理员角色，如果有，直接放过
                List<String> userRoleCodeList = userRoleInfoBiz.getRoleListByUserCode(userCode);
                if (CollectionUtils.isEmpty(userRoleCodeList)) {
                    log.warn("用户 : {} ,没有任何角色信息，不予访问", userCode);
                    return false;
                }
                if (userRoleCodeList.contains(SUPER_MANAGER_ROLE_CODE)) {
                    log.warn("尊敬的超级管理员用户 : {} ,欢迎您的访问,记得五星好评哟，萌萌哒~~~~", userCode);
                    return true;
                }
                List<String> userPermissions = getUserPermissions(userCode);
                return Safes.of(userPermissions).contains(servletPath);
            }
        }.execute();
    }

    /**
     * 根据code删除权限信息
     *
     * @param permissionCode permissionCode
     * @return success true orElse false
     */
    @Override
    public Boolean deleteByCode(String permissionCode) {
        log.info("deleteByCode biz start , permissionCode : {}", permissionCode);
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected Boolean process() {
                return permissionInfoService.deleteByCode(permissionCode);
            }
        }.execute();
    }
}

