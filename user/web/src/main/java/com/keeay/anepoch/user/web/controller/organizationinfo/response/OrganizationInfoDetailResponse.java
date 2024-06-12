
package com.keeay.anepoch.user.web.controller.organizationinfo.response;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author AI Admin
 */
@Data
public class OrganizationInfoDetailResponse {
        private Long id;

        private String code;

        private String parentCode;

        private String name;

        private Integer showOrder;

        private Boolean activeStatus;

        private String remark;

        private String createUser;

        private java.util.Date createTime;

        private String updateUser;

        private java.util.Date updateTime;


    @Override
    public String toString () {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

