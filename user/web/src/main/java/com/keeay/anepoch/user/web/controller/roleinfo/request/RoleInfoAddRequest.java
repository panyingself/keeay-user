package com.keeay.anepoch.user.web.controller.roleinfo.request;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class RoleInfoAddRequest {
    private Long id;

    private String roleCode;
    /**
     * 菜单编码集合
     */
    private List<String> menuCodeList;

    private String roleName;

    private String remark;

    private Integer activeStatus;

    private String createUser;

    private java.util.Date createTime;

    private String updateUser;

    private java.util.Date updateTime;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
