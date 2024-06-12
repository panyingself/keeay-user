package com.keeay.anepoch.user.biz.menupermissioninfo.converter;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.menupermissioninfo.bo.*;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import java.util.Objects;
import lombok.Data;
/**
 * @author py
 * @date 2019/4
 */
@Data
public class MenuPermissionInfoBoConverter {
	public static MenuPermissionInfo convertToMenuPermissionInfo(MenuPermissionInfoBo targetBo) {
		if (Objects.isNull(targetBo)) {
			return null;
		}
		return JsonMoreUtils.toBean(JsonMoreUtils.toJson(targetBo),MenuPermissionInfo.class);
		//或者构建你的实际逻辑
//		return MenuPermissionInfo.builder()
////				//fit your code
////				.build();
	}
}


