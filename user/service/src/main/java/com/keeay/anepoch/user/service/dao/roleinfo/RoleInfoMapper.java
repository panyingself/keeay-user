package com.keeay.anepoch.user.service.dao.roleinfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.dao.BaseMapper;
import com.keeay.anepoch.user.service.model.query.RoleInfoQuery;
import com.keeay.anepoch.user.service.model.query.UserInfoQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Repository
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {
    /**
     * 分页查询
     *
     * @param roleInfoQuery roleInfoQuery
     * @return data list
     */
    List<RoleInfo> pageList(RoleInfoQuery roleInfoQuery);

    /**
     * 根据codeList查询list
     *
     * @param codeList codeList
     * @return data list
     */
    List<RoleInfo> getListByCodeList(@Param("codeList") List<String> codeList);
}
