
package com.keeay.anepoch.user.biz.userinfo;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.user.biz.userinfo.helper.UserInfoHelper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author py
 * @date 2019/4
 */
@Service
@Slf4j
public class UserInfoBizImpl implements UserInfoBiz {
    @Resource
    private UserInfoService userInfoService;

    /**
     * 新增 record
     *
     * @param addUserInfoBo addUserInfoBo
     * @return success true orElse false
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean add(UserInfoBo addUserInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(addUserInfoBo), "addUserInfoBo is null");
            }

            @Override
            protected Boolean process() {
                //新增角色信息
                UserInfo newUserInfo = UserInfoBoConverter.convertToUserInfo(addUserInfoBo);
                //验证登录名、手机号是否有重复
                if (StringUtils.isNotBlank(newUserInfo.getLoginPwd())) {
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    newUserInfo.setLoginPwd(passwordEncoder.encode(newUserInfo.getLoginPwd()));
                }
                //生成userCode
                if(StringUtils.isBlank(newUserInfo.getUserCode())){
                    newUserInfo.setUserCode(UserInfoHelper.genUserCode());
                }
                userInfoService.insert(newUserInfo);
                return true;
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
    public boolean editById(UserInfoBo editUserInfoBo) {
        return new BaseBizTemplate<Boolean>() {
            @Override
            protected void checkParam() {
                ConditionUtils.checkArgument(Objects.nonNull(editUserInfoBo), "editUserInfoBo is null");
                ConditionUtils.checkArgument(Objects.nonNull(editUserInfoBo.getId()), "editUserInfoBo id is null");
            }

            @Override
            protected Boolean process() {
                UserInfo oldUserInfo = userInfoService.getDetailById(editUserInfoBo.getId());
                ConditionUtils.checkArgument(Objects.nonNull(oldUserInfo), "oldUserInfo is null");
                //修改记录
                UserInfo waitToUpdate = UserInfoBoConverter.convertToUserInfo(editUserInfoBo);
                userInfoService.update(waitToUpdate);
                return true;
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
                return JsonMoreUtils.toBean(JsonMoreUtils.toJson(fromDb), UserInfoBo.class);
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
                List<UserInfoBo> list = JsonMoreUtils.ofList(JsonMoreUtils.toJson(dataList), UserInfoBo.class);
                return new CommonPage<>(pageResult.getCurrentPage(), pageResult.getPageSize(), pageResult.getTotalCount(), list);
            }
        }.execute();
    }
}

