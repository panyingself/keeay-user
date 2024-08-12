package com.keeay.anepoch.user.service.service.userroleinfo;

import com.google.common.collect.Lists;
import com.keeay.anepoch.user.service.dao.userroleinfo.UserRoleInfoMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.keeay.anepoch.user.service.service.BaseServiceImpl;
import com.keeay.anepoch.user.service.model.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author pany
 */
@Service
public class UserRoleInfoServiceImpl extends BaseServiceImpl<UserRoleInfo, Long> implements UserRoleInfoService {
    @Resource
    private UserRoleInfoMapper userRoleInfoMapper;

    /**
     * 根据userCodeList查询list
     *
     * @param userCodeList userCodeList
     * @return list
     */
    @Override
    public List<UserRoleInfo> getListByUserCodeList(List<String> userCodeList) {
        if (CollectionUtils.isEmpty(userCodeList)) {
            return Lists.newArrayListWithCapacity(0);
        }
        return userRoleInfoMapper.getListByUserCodeList(userCodeList);
    }

    /**
     * 根据UserCode修改数据
     *
     * @param userRoleInfo userRoleInfo
     * @return success true orElse false
     */
    @Override
    public Boolean updateByUserCode(UserRoleInfo userRoleInfo) {
        return userRoleInfoMapper.updateByUserCode(userRoleInfo);
    }
}
