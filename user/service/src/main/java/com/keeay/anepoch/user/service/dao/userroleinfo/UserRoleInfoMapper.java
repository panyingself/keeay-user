package com.keeay.anepoch.user.service.dao.userroleinfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.dao.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Repository
public interface UserRoleInfoMapper extends BaseMapper<UserRoleInfo> {
    /**
     * 根据userCodeList查询list
     *
     * @param userCodeList userCodeList
     * @return list
     */
    List<UserRoleInfo> getListByUserCodeList(@Param("userCodeList") List<String> userCodeList);

    /**
     * 根据UserCode修改数据
     *
     * @param userRoleInfo userRoleInfo
     * @return success true orElse false
     */
    public Boolean updateByUserCode(UserRoleInfo userRoleInfo);
}
