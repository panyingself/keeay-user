package com.keeay.anepoch.user.web.controller.permissioninfo.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class PermissionInfoEditRequest extends PermissionInfoAddRequest{
        private Long id;

        @Override
        public String toString() {
                return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
}



