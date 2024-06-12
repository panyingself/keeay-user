package com.keeay.anepoch.user.api.request;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Description: 手机号验证
 *
 * @author -  pany
 * Time - 2024/4/3 - 17:27
 */
@Data
public class LoginPhoneVerifyFeignRequest implements Serializable {
    /**
     * 手机号
     */
    private String phone;
    /**
     * 图形验证码
     */
    private String imageCaptcha;
    /**
     * 验证码
     */
    private String verifyCode;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
