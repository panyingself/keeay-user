package com.keeay.anepoch.user.biz.userinfo.bo;

import com.keeay.anepoch.base.commons.base.page.CommonPage;
import com.keeay.anepoch.user.biz.roleinfo.bo.RoleInfoBo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class UserInfoBo extends CommonPage implements Serializable {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 组织编码
     */
    private String organizationCode;
    /**
     * 组织名称
     */
    private String organizationName;
    private String organizationCodeLike;
    private String userNameKeyword;
    private String phoneKeyword;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 角色信息
     */
    private List<RoleInfoBo> roleList;
    /**
     * 角色编码
     */
    private List<String> roleCodeList;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 登录密码
     */
    private String loginPwd;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 性别，0 女 1 男
     */
    private Boolean gender;
    /**
     * 备注
     */
    private String remark;
    /**
     * 激活状态 0未激活，1激活
     */
    private Boolean activeStatus;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private java.util.Date createTime;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 修改时间
     */
    private java.util.Date updateTime;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

