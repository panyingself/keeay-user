package com.keeay.anepoch.user.service.model.query;

import com.keeay.anepoch.user.service.model.RoleInfo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/7/22 - 15:30
 */
@Data
public class RoleInfoQuery extends RoleInfo implements Serializable {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
