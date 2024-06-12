package com.keeay.anepoch.user.biz.organizationinfo.converter;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.biz.organizationinfo.bo.*;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import java.util.Objects;
import lombok.Data;
/**
 * @author AI Admin
 */
@Data
public class OrganizationInfoBoConverter {
	public static OrganizationInfo convertToOrganizationInfo(OrganizationInfoBo targetBo) {
		if (Objects.isNull(targetBo)) {
			return null;
		}
		return JsonMoreUtils.toBean(JsonMoreUtils.toJson(targetBo),OrganizationInfo.class);
		//或者构建你的实际逻辑
//		return OrganizationInfo.builder()
////				//fit your code
////				.build();
	}
}


