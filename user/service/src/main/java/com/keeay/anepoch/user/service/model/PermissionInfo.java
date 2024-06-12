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
public class PermissionInfo implements Serializable {
	/** 主键id */
	private Long id;
	/** 权限code */
	private String permissionCode;
	/** 权限名称 */
	private String permissionName;
	/** api接口路径 */
	private String uri;
	/** 创建时间 */
	private java.util.Date createTime;
	/** 创建人 */
	private String createUser;
	/** 更新时间 */
	private java.util.Date updateTime;
	/** 更新人 */
	private String updateUser;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}

