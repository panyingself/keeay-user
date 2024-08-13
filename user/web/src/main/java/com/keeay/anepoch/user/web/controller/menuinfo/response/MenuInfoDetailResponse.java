package com.keeay.anepoch.user.web.controller.menuinfo.response;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class MenuInfoDetailResponse {
    private Long id;

    private String menuCode;

    private String parentMenuCode;

    private String menuName;

    private String path;

    private String iconUrl;

    private Integer sort;

    private Integer type;

    private java.util.Date createTime;

    private String createUser;

    private java.util.Date updateTime;

    private String updateUser;

    private List<String> permissionList;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

