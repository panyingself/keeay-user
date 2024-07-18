package com.keeay.anepoch.user.service.dao.userinfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.dao.BaseMapper;
import com.keeay.anepoch.user.service.model.query.UserInfoQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo>{
    /**
     * 分页查询
     * @param userInfoQuery userInfoQuery
     * @return data list
     */
    List<UserInfo> pageList(UserInfoQuery userInfoQuery);
}
