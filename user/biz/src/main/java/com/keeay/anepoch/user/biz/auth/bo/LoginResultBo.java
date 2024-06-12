package com.keeay.anepoch.user.biz.auth.bo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/4/3 - 16:35
 */
@Data
public class LoginResultBo implements Serializable {
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 用户名
     */
    private String userName;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
