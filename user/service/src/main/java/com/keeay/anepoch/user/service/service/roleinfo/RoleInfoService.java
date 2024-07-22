package com.keeay.anepoch.user.service.service.roleinfo;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.model.query.RoleInfoQuery;
import com.keeay.anepoch.user.service.model.query.UserInfoQuery;
import com.keeay.anepoch.user.service.service.BaseService;
/**
 * @author pany
 */
public interface RoleInfoService extends BaseService<RoleInfo, Long>{

    /**
     * roleInfoQuery
     *
     * @param roleInfoQuery roleInfoQuery
     * @param pageNum  页码
     * @param pageSize 每页显示的条数
     * @return 用户列表
     */
    CommonPage<RoleInfo> pageList(RoleInfoQuery roleInfoQuery, Integer pageNum, Integer pageSize);
}
