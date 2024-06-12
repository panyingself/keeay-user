
package com.keeay.anepoch.user.web.controller.roleinfo;

import com.keeay.anepoch.user.biz.roleinfo.RoleInfoBiz;
import com.keeay.anepoch.user.biz.roleinfo.bo.RoleInfoBo;
import com.keeay.anepoch.user.web.controller.roleinfo.request.*;
import com.keeay.anepoch.user.web.controller.roleinfo.response.*;
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
@RequestMapping("api/role/info")
public class RoleInfoController {
    @Resource
    private RoleInfoBiz roleInfoBiz;


    /**
     * 新增记录
     *
     * @param addRoleInfoRequest addRoleInfoRequest
     * @return success true orElse false
     */
    @PostMapping("add")
    public boolean add(@RequestBody RoleInfoAddRequest addRoleInfoRequest) {
        RoleInfoBo addRoleInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(addRoleInfoRequest), RoleInfoBo.class);
        return roleInfoBiz.add(addRoleInfoBo);
    }

    /**
     * 编辑记录
     *
     * @param editRoleInfoRequest editRoleInfoRequest
     * @return success true orElse false
     */
    @PostMapping("editById")
    public boolean edit(@RequestBody RoleInfoEditRequest editRoleInfoRequest) {
        RoleInfoBo editRoleInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(editRoleInfoRequest), RoleInfoBo.class);
        return roleInfoBiz.editById(editRoleInfoBo);
    }

    /**
     * 记录列表
     *
     * @param queryRoleInfoRequest queryRoleInfoRequest
     * @return list
     */
    @PostMapping("list")
    public List<RoleInfoListResponse> list(@RequestBody RoleInfoQueryRequest queryRoleInfoRequest) {
        RoleInfoBo queryRoleInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(queryRoleInfoRequest), RoleInfoBo.class);
        List<RoleInfoBo> resultList = roleInfoBiz.list(queryRoleInfoBo);
        if (CollectionUtils.isEmpty(resultList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList), RoleInfoListResponse.class);
    }

    /**
     * 通过id获取数据详情
     *
     * @param recordId recordId
     * @return 数据详情
     */
    @GetMapping("fetchDetailById")
    public RoleInfoDetailResponse fetchDetailById(Long recordId) {
        RoleInfoBo result = roleInfoBiz.fetchDetailById(recordId);
        if (Objects.isNull(result)) {
            return null;
        }
        return JsonMoreUtils.toBean(JsonMoreUtils.toJson(result), RoleInfoDetailResponse.class);
    }
}

