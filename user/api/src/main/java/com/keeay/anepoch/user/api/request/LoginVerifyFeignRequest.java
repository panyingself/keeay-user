package com.keeay.anepoch.user.api.request;

import com.keeay.anepoch.user.api.enums.VerifyLoginTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/4/3 - 17:13
 */
@Data
public class LoginVerifyFeignRequest implements Serializable {
    /**
     * 验证方式: account/phone
     */
    private VerifyLoginTypeEnum type;
    private LoginAccountVerifyFeignRequest accountVerifyRequest;
    private LoginPhoneVerifyFeignRequest phoneVerifyRequest;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
