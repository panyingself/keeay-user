package com.keeay.anepoch.user.service.model;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class MenuInfo implements Serializable {
	/** 主键id */
	private Long id;
	/** 菜单code */
	private String menuCode;
	/** 父级菜单code */
	private String parentMenuCode;
	/** 出单名称 */
	private String menuName;
	/** 菜单路径 */
	private String path;
	/** 菜单icon地址 */
	private String iconUrl;
	/** 排序字段，越小越靠前 */
	private Integer sort;
	/** 菜单类型:  0 - 目录, 1 - 菜单 , 2 - 按钮 */
	private Integer type;
	/** 创建时间 */
	private LocalDateTime createTime;
	/** 创建人 */
	private String createUser;
	/** 更新时间 */
	private LocalDateTime updateTime;
	/** 更新人 */
	private String updateUser;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}

