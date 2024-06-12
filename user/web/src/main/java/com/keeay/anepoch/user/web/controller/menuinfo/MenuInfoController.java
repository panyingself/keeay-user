
package com.keeay.anepoch.user.web.controller.menuinfo;

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
@RequestMapping("api/menu")
public class MenuInfoController {
    @Resource
    private MenuInfoBiz menuInfoBiz;


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

    /**
     * 权限菜单树查询(ALL)
     */
    @PostMapping("getTreeList")
    public List<MenuInfoListResponse> getTreeList() {
        List<MenuInfoBo> resultList = menuInfoBiz.getTreeList();
        if (CollectionUtils.isEmpty(resultList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList), MenuInfoListResponse.class);
    }

    @GetMapping("getUserTreeList")
    public List<MenuInfoListResponse> getUserTreeList(String userCode) {
        List<MenuInfoBo> resultList = menuInfoBiz.getUserTreeList(userCode);
        if (CollectionUtils.isEmpty(resultList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList), MenuInfoListResponse.class);
    }
}

