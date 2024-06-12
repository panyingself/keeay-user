package com.keeay.anepoch.user.web.controller.roleinfo.request;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class RoleInfoEditRequest extends RoleInfoAddRequest{
        private Long id;

        @Override
        public String toString() {
                return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
}



