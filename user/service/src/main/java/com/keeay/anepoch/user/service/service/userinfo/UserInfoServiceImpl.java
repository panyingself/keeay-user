package com.keeay.anepoch.user.service.service.userinfo;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.user.service.dao.userinfo.UserInfoMapper;
import com.keeay.anepoch.user.service.model.query.UserInfoQuery;
import org.springframework.stereotype.Service;
import com.keeay.anepoch.user.service.service.BaseServiceImpl;
import com.keeay.anepoch.user.service.model.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pany
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo,Long> implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;
    /**
     * 分页查询用户列表
     *
     * @param userInfoQuery 用户信息
     * @param pageNum  页码
     * @param pageSize 每页显示的条数
     * @return 用户列表
     */
    @Override
    public CommonPage<UserInfo> pageList(UserInfoQuery userInfoQuery, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserInfo> list = this.userInfoMapper.pageList(userInfoQuery);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(list);
        return new CommonPage<>((long)pageInfo.getPageNum(),(long)pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getList());
    }
}
