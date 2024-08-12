
package com.keeay.anepoch.user.web.controller.userinfo.response;

import com.keeay.anepoch.user.web.controller.roleinfo.response.RoleInfoDetailResponse;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class UserInfoListResponse {
    private Long id;

    private String organizationCode;
    /**
     * 机构名称
     */
    private String organizationName;
    /**
     * 角色信息
     */
    private List<RoleInfoDetailResponse> roleList;

    private String userCode;

    private String loginName;

    private String loginPwd;

    private String userName;

    private String phone;

    private String email;

    private Boolean gender;

    private String remark;

    private Boolean activeStatus;

    private String createUser;

    private java.util.Date createTime;

    private String updateUser;

    private java.util.Date updateTime;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
