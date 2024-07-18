package com.keeay.anepoch.user.service.model.query;

import com.keeay.anepoch.user.service.model.UserInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/7/18 - 17:15
 */
@Data
public class UserInfoQuery extends UserInfo {
    private String organizationCodeLike;
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
