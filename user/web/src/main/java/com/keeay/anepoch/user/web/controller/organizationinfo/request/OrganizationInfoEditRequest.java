package com.keeay.anepoch.user.web.controller.organizationinfo.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;

/**
 * @author AI Admin
 */
@Data
public class OrganizationInfoEditRequest extends OrganizationInfoAddRequest {
    private Long id;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}



