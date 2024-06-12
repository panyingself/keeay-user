package com.keeay.anepoch.user.web.controller.menuinfo.request;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class MenuInfoQueryRequest {
    private Long id;

    private String menuCode;

    private String parentMenuCode;

    private String menuName;

    private String path;

    private String iconUrl;

    private Integer sort;

    private Boolean type;

    private java.util.Date createTime;

    private String createUser;

    private java.util.Date updateTime;

    private String updateUser;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
