
package com.keeay.anepoch.user.biz.roleinfo;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.user.biz.roleinfo.bo.*;

import java.util.List;


/**
 * @author py
 * @date 2019/4
 */
public interface RoleInfoBiz {
    /**
     * 新增 record
     *
     * @param addRoleInfoBo addRoleInfoBo
     * @return success true orElse false
     */
    boolean add(RoleInfoBo addRoleInfoBo);

    /**
     * 修改 record
     *
     * @param editRoleInfoBo editRoleInfoBo
     * @return success true orElse false
     */
    boolean editById(RoleInfoBo editRoleInfoBo);

    /**
     * 查询record集合
     *
     * @param queryRoleInfoBo queryRoleInfoBo
     * @return record list
     */
    List<RoleInfoBo> list(RoleInfoBo queryRoleInfoBo);

    /**
     * 查询record集合
     *
     * @param queryRoleInfoBo queryRoleInfoBo
     * @return record list
     */
    CommonPage<RoleInfoBo> pageList(RoleInfoBo queryRoleInfoBo);

    /**
     * 查询record detail
     *
     * @param recordId recordId
     * @return record detail
     */
    RoleInfoBo fetchDetailById(Long recordId);

    /**
     * 根据codeList查询list
     *
     * @param codeList codeList
     * @return list
     */
    List<RoleInfoBo> fetchListByCodeList(List<String> codeList);
}

