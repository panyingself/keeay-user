
package com.keeay.anepoch.user.web.controller.menuinfo;

import com.keeay.anepoch.auth.api.context.LoginUser;
import com.keeay.anepoch.auth.api.context.UserContext;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.keeay.anepoch.user.biz.menuinfo.MenuInfoBiz;
import com.keeay.anepoch.user.biz.menuinfo.bo.MenuInfoBo;
import com.keeay.anepoch.user.web.controller.menuinfo.request.*;
import com.keeay.anepoch.user.web.controller.menuinfo.response.*;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pany
 */
@RestController
@RequestMapping("api/menu/info")
public class MenuInfoController {
    @Resource
    private MenuInfoBiz menuInfoBiz;

    @PostMapping("fetchDetailByCode")
    public MenuInfoDetailResponse fetchDetailByCode(@RequestBody MenuInfoAddRequest addMenuInfoRequest) {
        MenuInfoBo menuInfoBo = menuInfoBiz.fetchDetailByCode(addMenuInfoRequest.getMenuCode());
        return JsonMoreUtils.toBean(JsonMoreUtils.toJson(menuInfoBo), MenuInfoDetailResponse.class);
    }

    @PostMapping("add")
    public boolean add(@RequestBody MenuInfoAddRequest addMenuInfoRequest) {
        MenuInfoBo addMenuInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(addMenuInfoRequest), MenuInfoBo.class);
        return menuInfoBiz.add(addMenuInfoBo);
    }

    @PostMapping("editById")
    public boolean edit(@RequestBody MenuInfoEditRequest editMenuInfoRequest) {
        MenuInfoBo editMenuInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(editMenuInfoRequest), MenuInfoBo.class);
        return menuInfoBiz.editById(editMenuInfoBo);
    }

    @PostMapping("removeByCode")
    public boolean deleteByCode(@RequestBody MenuInfoEditRequest editMenuInfoRequest) {
        return menuInfoBiz.removeByCode(editMenuInfoRequest.getMenuCode());
    }

    /**
     * 权限菜单树查询(ALL)
     */
    @PostMapping("getTreeList")
    public List<MenuInfoListResponse> getTreeList() {
        List<MenuInfoBo> resultList = menuInfoBiz.fetchTreeList();
        if (CollectionUtils.isEmpty(resultList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList), MenuInfoListResponse.class);
    }

    @GetMapping("getUserTreeList")
    public List<MenuInfoListResponse> getUserTreeList() {
        LoginUser loginUser = UserContext.getUser();
        List<MenuInfoBo> resultList = menuInfoBiz.fetchUserTreeList(loginUser.getUserCode());
        if (CollectionUtils.isEmpty(resultList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList), MenuInfoListResponse.class);
    }
}

