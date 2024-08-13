package com.keeay.anepoch.user.service.model.query;

import com.keeay.anepoch.user.service.model.PermissionInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/8/13 - 15:29
 */
@Data
public class PermissionInfoQuery extends PermissionInfo {
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
