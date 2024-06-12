package com.keeay.anepoch.user.service.model;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class MenuPermissionInfo implements Serializable {
	/** 主键id */
	private Long id;
	/** 菜单编码 */
	private String menuCode;
	/** 权限编码集合 */
	private String permissionCodeList;
	/** 备注 */
	private String remark;
	/** 创建人 */
	private String createUser;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 修改人 */
	private String updateUser;
	/** 修改时间 */
	private java.util.Date updateTime;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}

