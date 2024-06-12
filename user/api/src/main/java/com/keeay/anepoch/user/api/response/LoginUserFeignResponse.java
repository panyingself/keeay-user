package com.keeay.anepoch.user.api.response;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/5/15 - 14:17
 */
@Data
@Builder
public class LoginUserFeignResponse implements Serializable {
    public LoginUserFeignResponse() {
    }

    public LoginUserFeignResponse(String userName, String userCode) {
        this.userName = userName;
        this.userCode = userCode;
    }

    private String userName;
    private String userCode;
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
