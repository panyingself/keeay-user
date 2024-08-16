
package com.keeay.anepoch.user.biz.userinfo;

import com.google.common.collect.Sets;
import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.base.commons.lang.Safes;
import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.base.commons.utils.JoinerUtils;
import com.keeay.anepoch.user.biz.organizationinfo.OrganizationInfoBiz;
import com.keeay.anepoch.user.biz.organizationinfo.bo.OrganizationInfoBo;
import com.keeay.anepoch.user.biz.roleinfo.bo.RoleInfoBo;
import com.keeay.anepoch.user.biz.userinfo.helper.UserInfoHelper;
import com.keeay.anepoch.user.biz.userroleinfo.UserRoleInfoBiz;
import com.keeay.anepoch.user.biz.userroleinfo.bo.UserRoleInfoBo;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.userinfo.bo.*;
import com.keeay.anepoch.user.service.model.query.UserInfoQuery;
import com.keeay.anepoch.user.service.service.userinfo.UserInfoService;
import com.keeay.anepoch.user.biz.userinfo.converter.UserInfoBoConverter;
import com.keeay.anepoch.base.commons.monitor.BaseBizTemplate;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author py
 * @date 2019/4
 */
@Service
@Slf4j
public class UserInfoBizImpl implements UserInfoBiz {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private OrganizationInfoBiz organizationInfoBiz;
    @Resource
    private UserRoleInfoBiz userRoleInfoBiz;

    /**
     * 新增 record
     *
     * @param addUserInfoBo addUserInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean add(UserInfoBo addUserInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(addUserInfoBo), "addUserInfoBo is null");
            }

            @Override
            protected Boolean process() {
                //生成userCode
                if (StringUtils.isBlank(addUserInfoBo.getUserCode())) {
                    addUserInfoBo.setUserCode(UserInfoHelper.genUserCode());
                }
                //新增角色信息
                UserInfo newUserInfo = UserInfoBoConverter.convertToUserInfo(addUserInfoBo);
                //验证登录名、手机号是否有重复
                if (StringUtils.isNotBlank(newUserInfo.getLoginPwd())) {
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    newUserInfo.setLoginPwd(passwordEncoder.encode(newUserInfo.getLoginPwd()));
                }
                userInfoService.insert(newUserInfo);
                //如果有角色分配，新增角色信息
                insertRoleData(addUserInfoBo);
                return true;
            }

            private void insertRoleData(UserInfoBo addUserInfoBo) {
                if (CollectionUtils.isEmpty(addUserInfoBo.getRoleCodeList())) {
                    return;
                }
                UserRoleInfoBo addUserRoleInfoBo = new UserRoleInfoBo();
                addUserRoleInfoBo.setUserCode(addUserInfoBo.getUserCode());
                addUserRoleInfoBo.setRoleCodeList(JoinerUtils.JOINER_COMMA.join(Safes.of(addUserInfoBo.getRoleCodeList())));
                userRoleInfoBiz.add(addUserRoleInfoBo);
            }
        }.execute();
    }

    /**
     * 修改 record
     *
     * @param editUserInfoBo editUserInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean editById(UserInfoBo editUserInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editUserInfoBo), "editUserInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editUserInfoBo.getId()), "editUserInfoBo id is null");
            }

            @Override
            protected Boolean process() {
                UserInfoBo oldDataFromDb = fetchDetailById(editUserInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldDataFromDb), "oldDataFromDb is null");
                //修改记录
                UserInfo waitToUpdate = UserInfoBoConverter.convertToUserInfo(editUserInfoBo);
                //修改角色信息
                this.editRoleData(editUserInfoBo, oldDataFromDb);
                userInfoService.update(waitToUpdate);
                return true;
            }

            private void editRoleData(UserInfoBo editUserInfoBo, UserInfoBo oldUserInfo) {
                HashSet<String> newRoleCodeList = Sets.newHashSet(Safes.of(editUserInfoBo.getRoleCodeList()));
                HashSet<String> oldRoleCodeList = Sets.newHashSet(Safes.of(oldUserInfo.getRoleCodeList()));
                if (Sets.symmetricDifference(newRoleCodeList, oldRoleCodeList).isEmpty()) {
                    log.info("editById editRoleData fast end, newRoleCodeList is same as oldRoleCodeList");
                    return;
                }
                //更新角色信息
                UserRoleInfoBo saveUserRoleInfoBo = new UserRoleInfoBo();
                saveUserRoleInfoBo.setUserCode(oldUserInfo.getUserCode());
                saveUserRoleInfoBo.setRoleCodeList(JoinerUtils.JOINER_COMMA.join(Safes.of(editUserInfoBo.getRoleCodeList())));
                userRoleInfoBiz.saveByUserCode(saveUserRoleInfoBo);
            }
        }.execute();
    }

    /**
     * 修改 record active状态
     *
     * @param editUserInfoBo editUserInfoBo
     * @return success true orElse false
     */
    @Override
    public Boolean changeEnable(UserInfoBo editUserInfoBo) {
        log.info("changeEnable biz start, editUserInfoBo : {}", editUserInfoBo);
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editUserInfoBo), "editUserInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editUserInfoBo.getId()), "editUserInfoBo id is null");
            }

            @Override
            protected Boolean process() {
                UserInfoBo oldDataFromDb = fetchDetailById(editUserInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldDataFromDb), "oldDataFromDb is null");
                //修改记录
                UserInfo waitToUpdate = UserInfoBoConverter.convertToUserInfo(editUserInfoBo);
                userInfoService.update(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 修改 record active状态
     *
     * @param editUserInfoBo editUserInfoBo
     * @return success true orElse false
     */
    @Override
    public Boolean resetPassword(UserInfoBo editUserInfoBo) {
        log.info("resetPassword biz start, editUserInfoBo : {}", editUserInfoBo);
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editUserInfoBo), "editUserInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editUserInfoBo.getId()), "editUserInfoBo id is null");
                ConditionUtils.checkArgument(StringUtils.isNotBlank(editUserInfoBo.getLoginPwd()), "editUserInfoBo loginPwd is null");

            }

            @Override
            protected Boolean process() {
                UserInfoBo oldDataFromDb = fetchDetailById(editUserInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldDataFromDb), "oldDataFromDb is null");
                //修改记录
                UserInfo waitToUpdate = UserInfoBoConverter.convertToUserInfo(editUserInfoBo);
                if (StringUtils.isNotBlank(editUserInfoBo.getLoginPwd())) {
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    waitToUpdate.setLoginPwd(passwordEncoder.encode(editUserInfoBo.getLoginPwd()));
                }
                userInfoService.update(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 新增 record
     *
     * @param userCode userCode
     * @return success true orElse false
     */
    @Override
    public Boolean removeByUserCode(String userCode) {
        log.info("removeByUserCode biz start, userCode : {}", userCode);
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected Boolean process() {
                ConditionUtils.checkArgument(StringUtils.isNotBlank(userCode), "userCode is not allowed blank");
                return userInfoService.deleteByUserCode(userCode);
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @param queryUserInfoBo queryUserInfoBo
     * @return record list
     */
    @Override
    public List<UserInfoBo> list(UserInfoBo queryUserInfoBo) {
        return new BaseBizTemplate<List<UserInfoBo>>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(queryUserInfoBo), "queryUserInfoBo is null");
            }

            @Override
            protected List<UserInfoBo> process() {
                UserInfo userInfoQuery = UserInfoBoConverter.convertToUserInfo(queryUserInfoBo);
                List<UserInfo> fromDbList = userInfoService.list(userInfoQuery);
                if (CollectionUtils.isEmpty(fromDbList)) {
                    return Lists.newArrayList();
                }
                return JsonMoreUtils.ofList(JsonMoreUtils.toJson(fromDbList), UserInfoBo.class);
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
    public UserInfoBo fetchDetailById(Long recordId) {
        return new BaseBizTemplate<UserInfoBo>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(recordId), "recordId is null");
            }

            @Override
            protected UserInfoBo process() {
                UserInfo fromDb = userInfoService.getDetailById(recordId);
                if (Objects.isNull(fromDb)) {
                    return null;
                }
                UserInfoBo userInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(fromDb), UserInfoBo.class);
                //wrap organization data
                wrapOrganizationData(Lists.newArrayList(userInfoBo));
                //wrap role data
                wrapRoleData(Lists.newArrayList(userInfoBo));
                return userInfoBo;
            }
        }.execute();
    }

    /**
     * 分页查询列表
     *
     * @param pageQueryBo pageQueryBo
     * @return page list
     */
    @Override
    public CommonPage<UserInfoBo> pageList(UserInfoBo pageQueryBo) {
        return new BaseBizTemplate<CommonPage<UserInfoBo>>() {
            @Override
            protected CommonPage<UserInfoBo> process() {
                UserInfoQuery userInfoQuery = JsonMoreUtils.toBean(JsonMoreUtils.toJson(pageQueryBo), UserInfoQuery.class);
                CommonPage<UserInfo> pageResult = userInfoService.pageList(userInfoQuery, pageQueryBo.getCurrentPage().intValue(), pageQueryBo.getPageSize().intValue());
                List<UserInfo> dataList = pageResult.getDataList();
                if (CollectionUtils.isEmpty(dataList)) {
                    new CommonPage<>(pageResult.getCurrentPage(), pageResult.getPageSize(), pageResult.getTotalCount(), Lists.newArrayList());
                }
                //convert
                List<UserInfoBo> list = JsonMoreUtils.ofList(JsonMoreUtils.toJson(dataList), UserInfoBo.class);
                //wrap organization data
                wrapOrganizationData(list);
                //wrap role data
                wrapRoleData(list);
                return new CommonPage<>(pageResult.getCurrentPage(), pageResult.getPageSize(), pageResult.getTotalCount(), list);
            }


        }.execute();
    }

    private void wrapOrganizationData(List<UserInfoBo> userDataList) {
        List<String> organizationCodeList = userDataList.stream().map(UserInfoBo::getOrganizationCode).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(organizationCodeList)) {
            log.warn("wrapOrganizationData fast end, organizationCodeList is empty");
            return;
        }
        List<OrganizationInfoBo> organizationInfoDataList = organizationInfoBiz.fetchListByCodes(organizationCodeList);
        if (CollectionUtils.isEmpty(organizationInfoDataList)) {
            log.warn("wrapOrganizationData fast end, organizationInfoDataList is empty");
            return;
        }
        Map<String, OrganizationInfoBo> dataMap = organizationInfoDataList.stream()
                .collect(Collectors.toMap(OrganizationInfoBo::getCode, data -> data, (k, v) -> k));
        userDataList.forEach(userInfoBo -> {
            Optional.ofNullable(dataMap.get(userInfoBo.getOrganizationCode())).ifPresent(data -> {
                userInfoBo.setOrganizationName(data.getName());
            });
        });
    }

    private void wrapRoleData(List<UserInfoBo> userDataList) {
        List<String> userCodeList = userDataList.stream().map(UserInfoBo::getUserCode).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userCodeList)) {
            log.warn("wrapRoleData fast end , userCodeList is empty");
            return;
        }
        List<UserRoleInfoBo> userRoleInfoBoList = userRoleInfoBiz.fetchListByUserCodes(userCodeList);
        if (CollectionUtils.isEmpty(userRoleInfoBoList)) {
            log.warn("wrapRoleData fast end , userRoleInfoBoList is empty");
            return;
        }
        Map<String, UserRoleInfoBo> userRoleInfoBoMap = userRoleInfoBoList.stream()
                .collect(Collectors.toMap(UserRoleInfoBo::getUserCode, data -> data, (k, v) -> k));
        userDataList.forEach(userInfoBo -> {
            Optional.ofNullable(userRoleInfoBoMap.get(userInfoBo.getUserCode())).ifPresent(userRoleInfoBo -> {
                userInfoBo.setRoleList(userRoleInfoBo.getRoleInfoBoList());
                if (CollectionUtils.isNotEmpty(userRoleInfoBo.getRoleInfoBoList())) {
                    userInfoBo.setRoleCodeList(userRoleInfoBo.getRoleInfoBoList().stream().map(RoleInfoBo::getRoleCode).collect(Collectors.toList()));
                }
            });

        });
    }
}

