
package com.keeay.anepoch.user.web.controller.userroleinfo;

import com.keeay.anepoch.user.biz.userroleinfo.UserRoleInfoBiz;
import com.keeay.anepoch.user.biz.userroleinfo.bo.UserRoleInfoBo;
import com.keeay.anepoch.user.web.controller.userroleinfo.request.*;
import com.keeay.anepoch.user.web.controller.userroleinfo.response.*;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author pany
 */
@RestController
@RequestMapping("api/userRole/info")
public class UserRoleInfoController{
	@Resource
	private UserRoleInfoBiz userRoleInfoBiz;
	

	/**
	 * 新增记录
	 *
	 * @param addUserRoleInfoRequest addUserRoleInfoRequest
	 * @return success true orElse false
	 */
	@PostMapping("add")
	public boolean add(@RequestBody UserRoleInfoAddRequest addUserRoleInfoRequest) {
		UserRoleInfoBo addUserRoleInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(addUserRoleInfoRequest), UserRoleInfoBo.class);
		return userRoleInfoBiz.add(addUserRoleInfoBo);
	}

	/**
	 * 修改记录
	 *
	 * @param editUserRoleInfoRequest editUserRoleInfoRequest
	 * @return success true orElse false
	 */
	@PostMapping("editById")
	public boolean edit(@RequestBody UserRoleInfoEditRequest editUserRoleInfoRequest) {
		UserRoleInfoBo editUserRoleInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(editUserRoleInfoRequest), UserRoleInfoBo.class);
		return userRoleInfoBiz.editById(editUserRoleInfoBo);
	}

	/**
	 * 记录列表
	 *
	 * @param queryUserRoleInfoRequest queryUserRoleInfoRequest
	 * @return list
	 */
	@PostMapping("list")
	public List<UserRoleInfoListResponse> list(@RequestBody UserRoleInfoQueryRequest queryUserRoleInfoRequest) {
		UserRoleInfoBo queryUserRoleInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(queryUserRoleInfoRequest), UserRoleInfoBo.class);
		List<UserRoleInfoBo> resultList = userRoleInfoBiz.list(queryUserRoleInfoBo);
		if(CollectionUtils.isEmpty(resultList)){
			return Lists.newArrayListWithCapacity(0);
		}
		return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList),UserRoleInfoListResponse.class);
	}

	/**
	 * 通过id获取数据详情
	 *
	 * @param recordId recordId
	 * @return 数据详情
	 */
	@GetMapping("fetchDetailById")
	public UserRoleInfoDetailResponse fetchDetailById(Long recordId) {
		UserRoleInfoBo result = userRoleInfoBiz.fetchDetailById(recordId);
		if(Objects.isNull(result)){
			return null;
		}
		return JsonMoreUtils.toBean(JsonMoreUtils.toJson(result),UserRoleInfoDetailResponse.class);
	}
}

