package com.keeay.anepoch.user.service.service.userroleinfo;

import com.keeay.anepoch.user.service.model.*;
import com.keeay.anepoch.user.service.service.BaseService;

import java.util.List;

/**
 * @author pany
 */
public interface UserRoleInfoService extends BaseService<UserRoleInfo, Long> {
    /**
     * 根据userCodeList查询list
     *
     * @param userCodeList userCodeList
     * @return list
     */
    List<UserRoleInfo> getListByUserCodeList(List<String> userCodeList);

    /**
     * 根据UserCode修改数据
     *
     * @param userRoleInfo userRoleInfo
     * @return success true orElse false
     */
    Boolean updateByUserCode(UserRoleInfo userRoleInfo);
}
