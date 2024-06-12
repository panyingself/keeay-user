package com.keeay.anepoch.user.biz.menuinfo.bo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author py
 * @date 2019/4
 */
@Data
public class MenuInfoBo implements Serializable {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 菜单code
     */
    private String menuCode;
    /**
     * 父级菜单code
     */
    private String parentMenuCode;
    /**
     * 出单名称
     */
    private String menuName;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 菜单icon地址
     */
    private String iconUrl;
    /**
     * 排序字段，越小越靠前
     */
    private Integer sort;
    /**
     * 菜单类型:  0 - 目录, 1 - 菜单 , 2 - 按钮
     */
    private Boolean type;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * children
     */
    private List<MenuInfoBo> children;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

