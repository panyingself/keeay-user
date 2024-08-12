
package com.keeay.anepoch.user.web.controller.userinfo;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.user.biz.userinfo.UserInfoBiz;
import com.keeay.anepoch.user.biz.userinfo.bo.UserInfoBo;
import com.keeay.anepoch.user.web.controller.userinfo.request.*;
import com.keeay.anepoch.user.web.controller.userinfo.response.*;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author pany
 */
@RestController
@RequestMapping("api/userinfo")
public class UserInfoController {
    @Resource
    private UserInfoBiz userInfoBiz;

    /**
     * 重置密码
     *
     * @param queryUserInfoRequest queryUserInfoRequest
     * @return list
     */
    @PostMapping("resetPassword")
    public List<UserInfoListResponse> resetPassword(@RequestBody UserInfoQueryRequest queryUserInfoRequest) {
        UserInfoBo queryUserInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(queryUserInfoRequest), UserInfoBo.class);
        List<UserInfoBo> resultList = userInfoBiz.list(queryUserInfoBo);
        if (CollectionUtils.isEmpty(resultList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList), UserInfoListResponse.class);
    }

    /**
     * 新增记录
     *
     * @param addUserInfoRequest addUserInfoRequest
     * @return success true orElse false
     */
    @PostMapping("add")
    public boolean add(@RequestBody UserInfoAddRequest addUserInfoRequest) {
        UserInfoBo addUserInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(addUserInfoRequest), UserInfoBo.class);
        return userInfoBiz.add(addUserInfoBo);
    }

    /**
     * 修改记录
     *
     * @param editUserInfoRequest editUserInfoRequest
     * @return success true orElse false
     */
    @PostMapping("editById")
    public boolean edit(@RequestBody UserInfoEditRequest editUserInfoRequest) {
        UserInfoBo editUserInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(editUserInfoRequest), UserInfoBo.class);
        return userInfoBiz.editById(editUserInfoBo);
    }

    /**
     * 修改记录
     *
     * @param editUserInfoRequest editUserInfoRequest
     * @return success true orElse false
     */
    @PostMapping("changeEnable")
    public boolean changeEnable(@RequestBody UserInfoEditRequest editUserInfoRequest) {
        UserInfoBo editUserInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(editUserInfoRequest), UserInfoBo.class);
        return userInfoBiz.changeEnable(editUserInfoBo);
    }

    /**
     * 新增记录
     *
     * @param addUserInfoRequest addUserInfoRequest
     * @return success true orElse false
     */
    @PostMapping("removeByUserCode")
    public boolean removeByUserCode(@RequestBody UserInfoAddRequest addUserInfoRequest) {
        return userInfoBiz.removeByUserCode(addUserInfoRequest.getUserCode());
    }

    /**
     * 记录列表
     *
     * @param queryUserInfoRequest queryUserInfoRequest
     * @return list
     */
    @PostMapping("list")
    public List<UserInfoListResponse> list(@RequestBody UserInfoQueryRequest queryUserInfoRequest) {
        UserInfoBo queryUserInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(queryUserInfoRequest), UserInfoBo.class);
        List<UserInfoBo> resultList = userInfoBiz.list(queryUserInfoBo);
        if (CollectionUtils.isEmpty(resultList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList), UserInfoListResponse.class);
    }

    /**
     * 通过id获取数据详情
     *
     * @param recordId recordId
     * @return 数据详情
     */
    @GetMapping("fetchDetailById")
    public UserInfoDetailResponse fetchDetailById(Long recordId) {
        UserInfoBo result = userInfoBiz.fetchDetailById(recordId);
        if (Objects.isNull(result)) {
            return null;
        }
        return JsonMoreUtils.toBean(JsonMoreUtils.toJson(result), UserInfoDetailResponse.class);
    }


    /**
     * 分页查询列表
     */
    @GetMapping("pageList")
    public CommonPage<UserInfoListResponse> pageList(UserInfoPageQueryRequest userInfoPageQueryRequest) {
        UserInfoBo userBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(userInfoPageQueryRequest), UserInfoBo.class);
        CommonPage<UserInfoBo> pageResult = userInfoBiz.pageList(userBo);
        List<UserInfoBo> dataList = pageResult.getDataList();
        if (CollectionUtils.isEmpty(dataList)) {
            return new CommonPage<>(pageResult.getCurrentPage(), pageResult.getPageSize(), pageResult.getTotalCount(), Lists.newArrayList());
        }
        List<UserInfoListResponse> responseList = JsonMoreUtils.ofList(JsonMoreUtils.toJson(dataList), UserInfoListResponse.class);
        return new CommonPage<>(pageResult.getCurrentPage(), pageResult.getPageSize(), pageResult.getTotalCount(), responseList);
    }
}

