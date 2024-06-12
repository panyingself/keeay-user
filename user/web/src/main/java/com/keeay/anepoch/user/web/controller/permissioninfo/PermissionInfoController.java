
package com.keeay.anepoch.user.web.controller.permissioninfo;

import com.keeay.anepoch.user.biz.permissioninfo.PermissionInfoBiz;
import com.keeay.anepoch.user.biz.permissioninfo.bo.PermissionInfoBo;
import com.keeay.anepoch.user.web.controller.permissioninfo.request.*;
import com.keeay.anepoch.user.web.controller.permissioninfo.response.*;
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
@RequestMapping("api/permission")
public class PermissionInfoController {
    @Resource
    private PermissionInfoBiz permissionInfoBiz;


    /**
     * 新增记录
     *
     * @param addPermissionInfoRequest addPermissionInfoRequest
     * @return success true orElse false
     */
    @PostMapping("add")
    public boolean add(@RequestBody PermissionInfoAddRequest addPermissionInfoRequest) {
        PermissionInfoBo addPermissionInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(addPermissionInfoRequest), PermissionInfoBo.class);
        return permissionInfoBiz.add(addPermissionInfoBo);
    }

    /**
     * 修改记录
     *
     * @param editPermissionInfoRequest editPermissionInfoRequest
     * @return success true orElse false
     */
    @PostMapping("editById")
    public boolean edit(@RequestBody PermissionInfoEditRequest editPermissionInfoRequest) {
        PermissionInfoBo editPermissionInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(editPermissionInfoRequest), PermissionInfoBo.class);
        return permissionInfoBiz.editById(editPermissionInfoBo);
    }

    /**
     * 记录列表
     *
     * @param queryPermissionInfoRequest queryPermissionInfoRequest
     * @return list
     */
    @PostMapping("list")
    public List<PermissionInfoListResponse> list(@RequestBody PermissionInfoQueryRequest queryPermissionInfoRequest) {
        PermissionInfoBo queryPermissionInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(queryPermissionInfoRequest), PermissionInfoBo.class);
        List<PermissionInfoBo> resultList = permissionInfoBiz.list(queryPermissionInfoBo);
        if (CollectionUtils.isEmpty(resultList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList), PermissionInfoListResponse.class);
    }

    /**
     * 通过id获取数据详情
     *
     * @param recordId recordId
     * @return 数据详情
     */
    @GetMapping("fetchDetailById")
    public PermissionInfoDetailResponse fetchDetailById(Long recordId) {
        PermissionInfoBo result = permissionInfoBiz.fetchDetailById(recordId);
        if (Objects.isNull(result)) {
            return null;
        }
        return JsonMoreUtils.toBean(JsonMoreUtils.toJson(result), PermissionInfoDetailResponse.class);
    }

    /**
     * 通过id获取数据详情
     *
     * @param userCode userCode
     * @return 数据详情
     */
    @GetMapping("getUserPermissions")
    public List<String> getUserPermissions(String userCode) {
        return permissionInfoBiz.getUserPermissions(userCode);
    }
}

