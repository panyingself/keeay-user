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
public class RoleInfo implements Serializable {
	/** 主键id */
	private Long id;
	/** 角色code */
	private String roleCode;
	/** 角色名称 */
	private String roleName;
	/** 备注 */
	private String remark;
	/**激活状态 1 激活 0 未激活*/
	private Integer activeStatus;
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

