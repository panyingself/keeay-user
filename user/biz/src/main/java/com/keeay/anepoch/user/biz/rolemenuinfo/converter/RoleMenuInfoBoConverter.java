package com.keeay.anepoch.user.biz.rolemenuinfo.converter;

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
		return JsonMoreUtils.toBean(JsonMoreUtils.toJson(targetBo),RoleMenuInfo.class);
		//或者构建你的实际逻辑
//		return RoleMenuInfo.builder()
////				//fit your code
////				.build();
	}
}


