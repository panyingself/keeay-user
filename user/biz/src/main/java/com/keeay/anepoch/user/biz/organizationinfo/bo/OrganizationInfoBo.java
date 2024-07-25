package com.keeay.anepoch.user.biz.organizationinfo.bo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * @author AI Admin
 */
@Data
public class OrganizationInfoBo implements Serializable {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 组织编码
     */
    private String code;
    /**
     * 父级组织编码(根节点-1)
     */
    private String parentCode;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 展示顺序(同级)
     */
    private Integer showOrder;
    /**
     * 激活状态 0未激活 1已激活
     */
    private Boolean activeStatus;
    /**
     * 备注
     */
    private String remark;
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

    private Boolean open;

    private List<OrganizationInfoBo> children;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

