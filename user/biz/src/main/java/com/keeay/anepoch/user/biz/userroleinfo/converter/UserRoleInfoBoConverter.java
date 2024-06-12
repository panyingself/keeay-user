package com.keeay.anepoch.user.biz.userroleinfo.converter;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.userroleinfo.bo.*;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import java.util.Objects;
import lombok.Data;
/**
 * @author py
 * @date 2019/4
 */
@Data
public class UserRoleInfoBoConverter {
	public static UserRoleInfo convertToUserRoleInfo(UserRoleInfoBo targetBo) {
		if (Objects.isNull(targetBo)) {
			return null;
		}
		return JsonMoreUtils.toBean(JsonMoreUtils.toJson(targetBo),UserRoleInfo.class);
		//或者构建你的实际逻辑
//		return UserRoleInfo.builder()
////				//fit your code
////				.build();
	}
}


