package com.keeay.anepoch.user.biz.userinfo.converter;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.userinfo.bo.*;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import java.util.Objects;
import lombok.Data;
/**
 * @author py
 * @date 2019/4
 */
@Data
public class UserInfoBoConverter {
	public static UserInfo convertToUserInfo(UserInfoBo targetBo) {
		return JsonMoreUtils.toBean(JsonMoreUtils.toJson(targetBo),UserInfo.class);
		//或者构建你的实际逻辑
//		return UserInfo.builder()
////				//fit your code
////				.build();
	}
}


