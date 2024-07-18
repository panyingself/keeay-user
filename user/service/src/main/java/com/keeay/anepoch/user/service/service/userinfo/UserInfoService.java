package com.keeay.anepoch.user.service.service.userinfo;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.model.query.UserInfoQuery;
import com.keeay.anepoch.user.service.service.BaseService;

/**
 * @author pany
 */
public interface UserInfoService extends BaseService<UserInfo, Long> {
    /**
     * 分页查询用户列表
     *
     * @param userInfoQuery 用户信息
     * @param pageNum  页码
     * @param pageSize 每页显示的条数
     * @return 用户列表
     */
    CommonPage<UserInfo> pageList(UserInfoQuery userInfoQuery, Integer pageNum, Integer pageSize);
}
