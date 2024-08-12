package com.keeay.anepoch.user.biz.rolemenuinfo.converter;

import com.keeay.anepoch.base.commons.utils.JoinerUtils;
import com.keeay.anepoch.base.commons.utils.SplitterUtils;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.rolemenuinfo.bo.*;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;

import java.util.Objects;

import lombok.Data;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class RoleMenuInfoBoConverter {
    public static RoleMenuInfo convertToRoleMenuInfo(RoleMenuInfoBo targetBo) {
        if (Objects.isNull(targetBo)) {
            return null;
        }
        RoleMenuInfo roleMenuInfo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(targetBo), RoleMenuInfo.class);
        roleMenuInfo.setMenuCodeListStr(JoinerUtils.JOINER_COMMA.join(targetBo.getMenuCodeList()));
        return roleMenuInfo;
    }

    public static RoleMenuInfoBo convertToRoleMenuInfoBo(RoleMenuInfo target) {
        if (Objects.isNull(target)) {
            return null;
        }
        RoleMenuInfoBo roleMenuInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(target), RoleMenuInfoBo.class);
        roleMenuInfoBo.setMenuCodeList(SplitterUtils.SPLITTER_COMMA.splitToList(target.getMenuCodeListStr()));
        return roleMenuInfoBo;
    }
}


