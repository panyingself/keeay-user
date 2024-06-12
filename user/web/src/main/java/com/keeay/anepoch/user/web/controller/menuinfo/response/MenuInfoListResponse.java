package com.keeay.anepoch.user.web.controller.menuinfo.response;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class MenuInfoListResponse {
    private Long id;

    private String menuCode;

    private String parentMenuCode;

    private String menuName;

    private String path;

    private String iconUrl;

    private Integer sort;

    private Boolean type;

    private LocalDateTime createTime;

    private String createUser;

    private LocalDateTime updateTime;

    private String updateUser;
    /**
     * children
     */
    private List<MenuInfoListResponse> children;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
