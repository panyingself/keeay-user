
package com.keeay.anepoch.user.web.controller.organizationinfo;

import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.user.biz.organizationinfo.OrganizationInfoBiz;
import com.keeay.anepoch.user.biz.organizationinfo.bo.OrganizationInfoBo;
import com.keeay.anepoch.user.web.controller.organizationinfo.request.*;
import com.keeay.anepoch.user.web.controller.organizationinfo.response.*;
import com.keeay.anepoch.base.commons.utils.JsonMoreUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author AI Admin
 */
@RestController
@RequestMapping("api/organization/info")
public class OrganizationInfoController{
	@Resource
	private OrganizationInfoBiz organizationInfoBiz;
	

	/**
	 * 新增记录
	 *
	 * @param addOrganizationInfoRequest addOrganizationInfoRequest
	 * @return success true orElse false
	 */
	@PostMapping("add")
	public boolean add(@RequestBody OrganizationInfoAddRequest addOrganizationInfoRequest) {
		OrganizationInfoBo addOrganizationInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(addOrganizationInfoRequest), OrganizationInfoBo.class);
		return organizationInfoBiz.add(addOrganizationInfoBo);
	}

	/**
	 * 修改记录
	 *
	 * @param editOrganizationInfoRequest editOrganizationInfoRequest
	 * @return success true orElse false
	 */
	@PostMapping("editById")
	public boolean edit(@RequestBody OrganizationInfoEditRequest editOrganizationInfoRequest) {
		OrganizationInfoBo editOrganizationInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(editOrganizationInfoRequest), OrganizationInfoBo.class);
		return organizationInfoBiz.editById(editOrganizationInfoBo);
	}

	/**
	 * 记录列表
	 *
	 * @param queryOrganizationInfoRequest queryOrganizationInfoRequest
	 * @return list
	 */
	@PostMapping("getTreeList")
	public List<OrganizationInfoListResponse> getTreeList(@RequestBody OrganizationInfoQueryRequest queryOrganizationInfoRequest) {
		OrganizationInfoBo queryOrganizationInfoBo = JsonMoreUtils.toBean(JsonMoreUtils.toJson(queryOrganizationInfoRequest), OrganizationInfoBo.class);
		List<OrganizationInfoBo> resultList = organizationInfoBiz.getTreeList(queryOrganizationInfoBo);
		if(CollectionUtils.isEmpty(resultList)){
			return Lists.newArrayListWithCapacity(0);
		}
		return JsonMoreUtils.ofList(JsonMoreUtils.toJson(resultList),OrganizationInfoListResponse.class);
	}

	/**
	 * 通过id获取数据详情
	 *
	 * @param recordId recordId
	 * @return 数据详情
	 */
	@GetMapping("fetchDetailById")
	public OrganizationInfoDetailResponse fetchDetailById(Long recordId) {
		OrganizationInfoBo result = organizationInfoBiz.fetchDetailById(recordId);
		if(Objects.isNull(result)){
			return null;
		}
		return JsonMoreUtils.toBean(JsonMoreUtils.toJson(result),OrganizationInfoDetailResponse.class);
	}
}

