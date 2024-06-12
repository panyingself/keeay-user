package com.keeay.anepoch.user.biz.auth;

import com.keeay.anepoch.base.commons.monitor.BaseBizTemplate;
import com.keeay.anepoch.base.commons.utils.ConditionUtils;
import com.keeay.anepoch.user.biz.userinfo.UserInfoBiz;
import com.keeay.anepoch.user.biz.userinfo.bo.UserInfoBo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/4/3 - 16:34
 */
@Slf4j
@Component
public class AuthBizImpl implements AuthBiz {
    @Resource
    private UserInfoBiz userInfoBiz;

    /**
     * 验证用户名密码登录
     *
     * @param loginName loginName
     * @param loginPwd  loginPwd
     * @return success true orElse false
     */
    @Override
    public UserInfoBo verifyAccountLogin(String loginName, String loginPwd) {
        log.info("verifyUserNameLogin biz start, loginName : {} , loginPwd : {}", loginName, loginPwd);
        return new BaseBizTemplate<UserInfoBo>() {
            @Override
            protected UserInfoBo process() {
                //根据用户名查询记录(用户名全局唯一)
                UserInfoBo queryUserInfoBo = new UserInfoBo();
                queryUserInfoBo.setLoginName(loginName);
                List<UserInfoBo> fromDbData = userInfoBiz.list(queryUserInfoBo);
                if (CollectionUtils.isEmpty(fromDbData)) {
                    log.warn("verifyUserNameLogin biz fast end, fromDbData is empty");
                    return null;
                }
                //对比校验
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                ConditionUtils.checkArgument(passwordEncoder.matches(loginPwd, fromDbData.get(0).getLoginPwd()), "密码不正确");
                return fromDbData.get(0);
            }
        }.execute();
    }

    /**
     * 验证用手机号登录
     *
     * @param phone      phone
     * @param verifyCode verifyCode
     * @return success true orElse false
     */
    @Override
    public UserInfoBo verifyPhoneLogin(String phone, String verifyCode) {
        log.info("verifyPhoneLogin biz start, phone : {} , verifyCode : {}", phone, verifyCode);
        return new BaseBizTemplate<UserInfoBo>() {
            @Override
            protected UserInfoBo process() {
                return null;
            }
        }.execute();
    }
}
