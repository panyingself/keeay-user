package com.keeay.anepoch.user.biz.auth;

import com.keeay.anepoch.user.biz.userinfo.bo.UserInfoBo;

/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/4/3 - 16:33
 */
public interface AuthBiz {
    /**
     * 验证用户名密码登录
     *
     * @param loginName loginName
     * @param loginPwd  loginPwd
     * @return success true orElse false
     */
    UserInfoBo verifyAccountLogin(String loginName, String loginPwd);

    /**
     * 验证用手机号登录
     *
     * @param phone      phone
     * @param verifyCode verifyCode
     * @return success true orElse false
     */
    UserInfoBo verifyPhoneLogin(String phone, String verifyCode);
}
