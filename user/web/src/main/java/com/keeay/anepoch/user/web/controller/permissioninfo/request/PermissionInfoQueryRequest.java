package com.keeay.anepoch.user.web.controller.permissioninfo.request;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class PermissionInfoQueryRequest {
    private Long id;

    private String permissionCode;

    private String permissionName;

    private String uri;

    private java.util.Date createTime;

    private String createUser;

    private java.util.Date updateTime;

    private String updateUser;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
