package com.keeay.anepoch.user.biz.userinfo.helper;

import java.util.UUID;

/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/7/19 - 14:00
 */
public final class UserInfoHelper {
    //设定用户code规则11位,10 + 系统上线时间 + 每日计数

    public static String genUserCode(){
        //fixme 这里先采取随机uuid
        UUID uuid = UUID.randomUUID();
        // 移除UUID中的破折号
        return uuid.toString().replace("-", "");
    }
}
