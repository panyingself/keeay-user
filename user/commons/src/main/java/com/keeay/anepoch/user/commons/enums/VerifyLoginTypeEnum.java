package com.keeay.anepoch.user.commons.enums;

/**
 * 登录方式
 * @author pany
 */
public enum VerifyLoginTypeEnum {
    /**
     * 账密登录
     */
    USER_NAME(1, "账密登录"),
    /**
     * 手机号登录
     */
    PHONE(2, "手机号登录"),
    ;
    private Integer code;
    private String desc;

    VerifyLoginTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public Integer code() {
        return code;
    }

    public String desc() {
        return desc;
    }
}
