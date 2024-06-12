
package com.keeay.anepoch.user.web.controller.rolemenuinfo;

import com.keeay.anepoch.user.biz.rolemenuinfo.RoleMenuInfoBiz;
import com.keeay.anepoch.user.biz.rolemenuinfo.bo.RoleMenuInfoBo;
import com.keeay.anepoch.user.web.controller.rolemenuinfo.request.*;
import com.keeay.anepoch.user.web.controller.rolemenuinfo.response.*;
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
@RequestMapping("api/roleMenu/info")
public class RoleMenuInfoController {
    @Resource
    private RoleMenuInfoBiz roleMenuInfoBiz;


    /**
     * 新增记录
     *
     * @param addRoleMenuInfoRequest addRoleMenuInfoRequest
     * @return success true orElse false
     */
    @PostMapping("add")
    public boolean add(@RequestBody RoleMenuInfoAddRequest addRoleMenuInfoRequest) {
        RoleMenuInfoBo addRoleMenuInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(addRoleMenuInfoRequest), RoleMenuInfoBo.class);
        return roleMenuInfoBiz.add(addRoleMenuInfoBo);
    }

    /**
     * 修改记录
     *
     * @param editRoleMenuInfoRequest editRoleMenuInfoRequest
     * @return success true orElse false
     */
    @PostMapping("editById")
    public boolean edit(@RequestBody RoleMenuInfoEditRequest editRoleMenuInfoRequest) {
        RoleMenuInfoBo editRoleMenuInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(editRoleMenuInfoRequest), RoleMenuInfoBo.class);
        return roleMenuInfoBiz.editById(editRoleMenuInfoBo);
    }

    /**
     * 记录列表
     *
     * @param queryRoleMenuInfoRequest queryRoleMenuInfoRequest
     * @return list
     */
    @PostMapping("list")
    public List<RoleMenuInfoListResponse> list(@RequestBody RoleMenuInfoQueryRequest queryRoleMenuInfoRequest) {
        RoleMenuInfoBo queryRoleMenuInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(queryRoleMenuInfoRequest), RoleMenuInfoBo.class);
        List<RoleMenuInfoBo> resultList = roleMenuInfoBiz.list(queryRoleMenuInfoBo);
        if (CollectionUtils.isEmpty(resultList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList), RoleMenuInfoListResponse.class);
    }

    /**
     * 通过id获取数据详情
     *
     * @param recordId recordId
     * @return 数据详情
     */
    @GetMapping("fetchDetailById")
    public RoleMenuInfoDetailResponse fetchDetailById(Long recordId) {
        RoleMenuInfoBo result = roleMenuInfoBiz.fetchDetailById(recordId);
        if (Objects.isNull(result)) {
            return null;
        }
        return JsonMoreUtils.toBean(JsonMoreUtils.toJson(result), RoleMenuInfoDetailResponse.class);
    }
}

