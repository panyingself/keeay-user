package com.keeay.anepoch.user.web.controller.userroleinfo.request;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class UserRoleInfoAddRequest {
    private Long id;

    private String userCode;

    private String roleCodeList;

    private String remark;

    private String createUser;

    private java.util.Date createTime;

    private String updateUser;

    private java.util.Date updateTime;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
