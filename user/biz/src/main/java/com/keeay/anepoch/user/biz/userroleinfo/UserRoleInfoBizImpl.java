
package com.keeay.anepoch.user.biz.userroleinfo;

import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.base.commons.utils.SplitterUtils;
import com.keeay.anepoch.user.biz.roleinfo.RoleInfoBiz;
import com.keeay.anepoch.user.biz.roleinfo.bo.RoleInfoBo;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.userroleinfo.bo.*;
import com.keeay.anepoch.user.service.service.userroleinfo.UserRoleInfoService;
import com.keeay.anepoch.user.biz.userroleinfo.converter.UserRoleInfoBoConverter;
import com.keeay.anepoch.base.commons.monitor.BaseBizTemplate;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class UserRoleInfoBizImpl implements UserRoleInfoBiz {
    @Resource
    private UserRoleInfoService userRoleInfoService;
    @Resource
    private RoleInfoBiz roleInfoBiz;

    /**
     * save record 有记录就修改，没记录就新增。
     *
     * @param saveUserRoleInfoBo addUserRoleInfoBo
     * @return success true orElse false
     */
    @Override
    public boolean saveByUserCode(UserRoleInfoBo saveUserRoleInfoBo) {
        log.info("saveByUserCode biz start , saveUserRoleInfoBo : {}", saveUserRoleInfoBo);
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(saveUserRoleInfoBo), "saveUserRoleInfoBo is null");
            }

            @Override
            protected Boolean process() {
                List<UserRoleInfo> oldUserRoleInfoList = userRoleInfoService.getListByUserCodeList(Lists.newArrayList(saveUserRoleInfoBo.getUserCode()));
                if (CollectionUtils.isEmpty(oldUserRoleInfoList)) {
                    add(saveUserRoleInfoBo);
                    log.info("saveByUserCode fast end , new record");
                    return true;
                }
                //修改记录
                UserRoleInfo waitToUpdate = UserRoleInfoBoConverter.convertToUserRoleInfo(saveUserRoleInfoBo);
                userRoleInfoService.updateByUserCode(waitToUpdate);
                log.info("saveByUserCode fast end , update record");
                return true;
            }
        }.execute();
    }

    /**
     * 新增 record
     *
     * @param addUserRoleInfoBo addUserRoleInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(UserRoleInfoBo addUserRoleInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(addUserRoleInfoBo), "addUserRoleInfoBo is null");
            }

            @Override
            protected Boolean process() {
                //新增角色信息
                UserRoleInfo newUserRoleInfo = UserRoleInfoBoConverter.convertToUserRoleInfo(addUserRoleInfoBo);
                userRoleInfoService.insert(newUserRoleInfo);
                return true;
            }
        }.execute();
    }

    /**
     * 修改 record
     *
     * @param editUserRoleInfoBo editUserRoleInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editById(UserRoleInfoBo editUserRoleInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editUserRoleInfoBo), "editUserRoleInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editUserRoleInfoBo.getId()), "editUserRoleInfoBo id is null");
            }

            @Override
            protected Boolean process() {
                UserRoleInfo oldUserRoleInfo = userRoleInfoService.getDetailById(editUserRoleInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldUserRoleInfo), "oldUserRoleInfo is null");
                //修改记录
                UserRoleInfo waitToUpdate = UserRoleInfoBoConverter.convertToUserRoleInfo(editUserRoleInfoBo);
                userRoleInfoService.update(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 修改 record
     *
     * @param editUserRoleInfoBo editUserRoleInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean editByUserCode(UserRoleInfoBo editUserRoleInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editUserRoleInfoBo), "editUserRoleInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editUserRoleInfoBo.getUserCode()), "editUserRoleInfoBo userCode is null");
            }

            @Override
            protected Boolean process() {
                List<UserRoleInfo> oldUserRoleInfoList = userRoleInfoService.getListByUserCodeList(Lists.newArrayList(editUserRoleInfoBo.getUserCode()));
                ConditionUtils.checkArgument(CollectionUtils.isNotEmpty(oldUserRoleInfoList), "oldUserRoleInfoList is empty");
                //修改记录
                UserRoleInfo waitToUpdate = UserRoleInfoBoConverter.convertToUserRoleInfo(editUserRoleInfoBo);
                userRoleInfoService.updateByUserCode(waitToUpdate);
                return true;
            }
        }.execute();
    }

    /**
     * 查询record集合
     *
     * @param queryUserRoleInfoBo queryUserRoleInfoBo
     * @return record list
     */
    @Override
    public List<UserRoleInfoBo> list(UserRoleInfoBo queryUserRoleInfoBo) {
        return new BaseBizTemplate<List<UserRoleInfoBo>>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(queryUserRoleInfoBo), "queryUserRoleInfoBo is null");
            }

            @Override
            protected List<UserRoleInfoBo> process() {
                UserRoleInfo userRoleInfoQuery = UserRoleInfoBoConverter.convertToUserRoleInfo(queryUserRoleInfoBo);
                List<UserRoleInfo> fromDbList = userRoleInfoService.list(userRoleInfoQuery);
                if (CollectionUtils.isEmpty(fromDbList)) {
                    return Lists.newArrayList();
                }
                return JsonMoreUtils.ofList(JsonMoreUtils.toJson(fromDbList), UserRoleInfoBo.class);
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
    public UserRoleInfoBo fetchDetailById(Long recordId) {
        return new BaseBizTemplate<UserRoleInfoBo>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(recordId), "recordId is null");
            }

            @Override
            protected UserRoleInfoBo process() {
                UserRoleInfo fromDb = userRoleInfoService.getDetailById(recordId);
                if (Objects.isNull(fromDb)) {
                    return null;
                }
                return JsonMoreUtils.toBean(JsonMoreUtils.toJson(fromDb), UserRoleInfoBo.class);
            }
        }.execute();
    }

    /**
     * 通过用户编码获取用户拥有的角色信息
     *
     * @param userCode userCode
     * @return role list
     */
    @Override
    public List<String> getRoleListByUserCode(String userCode) {
        log.info("getRoleListByUserCode biz fast start, userCode : {}", userCode);
        return new BaseBizTemplate<List<String>>() {
            @Override
            protected List<String> process() {
                ConditionUtils.checkArgument(StringUtils.isNotBlank(userCode), "userCode is blank");
                UserRoleInfo query = new UserRoleInfo();
                query.setUserCode(userCode);
                List<UserRoleInfo> listFromDb = userRoleInfoService.list(query);
                if (CollectionUtils.isEmpty(listFromDb)) {
                    log.info("getRoleListByUserCode biz fast end, listFromDb is empty");
                    return Lists.newArrayListWithCapacity(0);
                }
                String roleCodeListStr = listFromDb.get(0).getRoleCodeList();
                if (StringUtils.isBlank(roleCodeListStr)) {
                    log.info("getRoleListByUserCode biz fast end, roleCodeListStr is blank");
                    return Lists.newArrayListWithCapacity(0);
                }
                return SplitterUtils.SPLITTER_COMMA.splitToList(roleCodeListStr);
            }
        }.execute();
    }

    /**
     * 通过userCodes查询用户角色关联信息
     *
     * @param userCodes userCodes
     * @return list
     */
    @Override
    public List<UserRoleInfoBo> fetchListByUserCodes(List<String> userCodes) {
        log.info("fetchListByUserCodes biz start, userCodes : {}", userCodes);
        return new BaseBizTemplate<List<UserRoleInfoBo>>() {
            @Override
            protected List<UserRoleInfoBo> process() {
                if (CollectionUtils.isEmpty(userCodes)) {
                    return Lists.newArrayListWithCapacity(0);
                }
                List<UserRoleInfo> listFromDb = userRoleInfoService.getListByUserCodeList(userCodes);
                if (CollectionUtils.isEmpty(listFromDb)) {
                    log.warn("fetchListByUserCodes biz fast end, listFromDb is empty");
                    return Lists.newArrayListWithCapacity(0);
                }
                List<UserRoleInfoBo> resultList = JsonMoreUtils.ofList(JsonMoreUtils.toJson(listFromDb), UserRoleInfoBo.class);
                //查询角色信息
                List<String> roleCodeList = listFromDb.stream()
                        .map(UserRoleInfo::getRoleCodeList)
                        .map(SplitterUtils.SPLITTER_COMMA::splitToList)
                        .flatMap(Collection::stream)
                        .distinct()
                        .collect(Collectors.toList());
                List<RoleInfoBo> roleInfoBoList = roleInfoBiz.fetchListByCodeList(roleCodeList);
                if (CollectionUtils.isEmpty(roleInfoBoList)) {
                    log.warn("fetchListByUserCodes biz fast end, roleInfoBoList is empty");
                    return Lists.newArrayListWithCapacity(0);
                }
                Map<String, RoleInfoBo> roleMap = roleInfoBoList.stream()
                        .collect(Collectors.toMap(RoleInfoBo::getRoleCode, data -> data, (k, v) -> k));
                //wrap role
                resultList.forEach(data -> {
                    String roleStr = data.getRoleCodeList();
                    if (StringUtils.isBlank(roleStr)) {
                        return;
                    }
                    List<String> currentRoleCodeList = SplitterUtils.SPLITTER_COMMA.splitToList(roleStr);
                    List<RoleInfoBo> currentRoleList = Lists.newArrayList();
                    currentRoleCodeList.forEach(roleCode -> {
                        Optional.ofNullable(roleMap.get(roleCode)).ifPresent(currentRoleList::add);
                    });
                    data.setRoleInfoBoList(currentRoleList);

                });
                return resultList;
            }
        }.execute();
    }
}

