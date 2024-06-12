package com.keeay.anepoch.user.biz.menuinfo.converter;

import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.menuinfo.bo.*;

import java.util.Objects;

import lombok.Data;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class MenuInfoBoConverter {
    public static MenuInfo convertToMenuInfo(MenuInfoBo targetBo) {
        if (Objects.isNull(targetBo)) {
            return null;
        }
        return JsonMoreUtils.toBean(JsonMoreUtils.toJson(targetBo), MenuInfo.class);
        //或者构建你的实际逻辑
//		return MenuInfo.builder()
////				//fit your code
////				.build();
    }
}


