package com.keeay.anepoch.user.web.controller.menuinfo.request;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class MenuInfoAddRequest {
    private String menuCode;

    private String parentMenuCode;

    private List<String> permissionList;

    private String menuName;

    private String path;

    private String iconUrl;

    private Integer sort;

    private Integer type;

    private LocalDateTime createTime;

    private String createUser;

    private LocalDateTime updateTime;

    private String updateUser;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
