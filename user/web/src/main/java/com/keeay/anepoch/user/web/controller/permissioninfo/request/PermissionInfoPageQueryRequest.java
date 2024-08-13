package com.keeay.anepoch.user.web.controller.permissioninfo.request;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class PermissionInfoPageQueryRequest extends CommonPage {
    private String permissionNameKeyword;
    private String permissionCodeKeyword;
    private String uriKeyword;
    private LocalDateTime createTimeStart;
    private LocalDateTime createTimeEnd;
    private LocalDateTime updateTimeStart;
    private LocalDateTime updateTimeEnd;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
