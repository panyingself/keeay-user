
package com.keeay.anepoch.user.web.provider;

import com.keeay.anepoch.base.commons.exception.BizException;
import com.keeay.anepoch.user.api.enums.VerifyLoginTypeEnum;
import com.keeay.anepoch.user.api.request.LoginAccountVerifyFeignRequest;
import com.keeay.anepoch.user.api.request.LoginPhoneVerifyFeignRequest;
import com.keeay.anepoch.user.api.request.LoginVerifyFeignRequest;
import com.keeay.anepoch.user.api.response.LoginUserFeignResponse;
import com.keeay.anepoch.user.biz.auth.AuthBiz;
import com.keeay.anepoch.user.biz.permissioninfo.PermissionInfoBiz;
import com.keeay.anepoch.user.biz.userinfo.bo.UserInfoBo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author pany
 */
@RestController
@RequestMapping("feign/auth")
public class AuthProvider {
    @Resource
    private AuthBiz authBiz;
    @Resource
    private PermissionInfoBiz permissionInfoBiz;

    /**
     * 验证用户是否拥有servlet权限
     *
     * @param userCode    userCode
     * @param servletPath servletPath
     * @return 有 success 没有 false
     */
    @PostMapping("checkUserServletPermission")
    public Boolean checkUserServletPermission(@RequestParam("userCode") String userCode, @RequestParam("servletPath") String servletPath) {
        return permissionInfoBiz.checkUserServletPermission(userCode, servletPath);
    }

    /**
     * 验证用户登录
     *
     * @param loginVerifyFeignRequest loginVerifyFeignRequest
     * @return 有 success 没有 false
     */
    @PostMapping("getAndCheckUserLogin")
    public LoginUserFeignResponse getAndCheckUserLogin(@RequestBody LoginVerifyFeignRequest loginVerifyFeignRequest) {
        if (Objects.equals(VerifyLoginTypeEnum.ACCOUNT, loginVerifyFeignRequest.getType())) {
            return this.loginForAccount(loginVerifyFeignRequest);
        }
        if (Objects.equals(VerifyLoginTypeEnum.PHONE, loginVerifyFeignRequest.getType())) {
            return this.loginForPhone(loginVerifyFeignRequest);
        }
        throw new BizException("不支持的登录方式");
    }

    /**
     * 账户登录(用户名密码)
     *
     * @param loginVerifyFeignRequest loginVerifyFeignRequest
     * @return LoginUserFeignResponse
     */
    private LoginUserFeignResponse loginForAccount(LoginVerifyFeignRequest loginVerifyFeignRequest) {
        LoginAccountVerifyFeignRequest account = loginVerifyFeignRequest.getAccountVerifyRequest();
        UserInfoBo userInfoBo = authBiz.verifyAccountLogin(account.getLoginName(), account.getLoginPwd());
        if (Objects.isNull(userInfoBo)) {
            return null;
        }
        return LoginUserFeignResponse.builder()
                .userCode(userInfoBo.getUserCode())
                .userName(userInfoBo.getUserName())
                .build();
    }

    /**
     * 手机号登录
     *
     * @param loginVerifyFeignRequest loginVerifyFeignRequest
     * @return LoginUserFeignResponse
     */
    private LoginUserFeignResponse loginForPhone(LoginVerifyFeignRequest loginVerifyFeignRequest) {
        LoginPhoneVerifyFeignRequest phone = loginVerifyFeignRequest.getPhoneVerifyRequest();
        UserInfoBo userInfoBo = authBiz.verifyPhoneLogin(phone.getPhone(), phone.getVerifyCode());
        if (Objects.isNull(userInfoBo)) {
            return null;
        }
        return LoginUserFeignResponse.builder()
                .userCode(userInfoBo.getUserCode())
                .userName(userInfoBo.getUserName())
                .build();
    }

}

