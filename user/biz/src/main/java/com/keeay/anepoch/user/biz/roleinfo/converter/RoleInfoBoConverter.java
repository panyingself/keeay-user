package com.keeay.anepoch.user.biz.roleinfo.converter;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.roleinfo.bo.*;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import java.util.Objects;
import lombok.Data;
/**
 * @author py
 * @date 2019/4
 */
@Data
public class RoleInfoBoConverter {
	public static RoleInfo convertToRoleInfo(RoleInfoBo targetBo) {
		if (Objects.isNull(targetBo)) {
			return null;
		}
		return JsonMoreUtils.toBean(JsonMoreUtils.toJson(targetBo),RoleInfo.class);
		//或者构建你的实际逻辑
//		return RoleInfo.builder()
////				//fit your code
////				.build();
	}
}


