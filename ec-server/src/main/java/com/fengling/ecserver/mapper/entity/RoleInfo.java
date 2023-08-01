package com.fengling.ecserver.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fengling.ecserver.base.LavaDo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fengling
 * @since 2023-03-22
 */
@Data
@Accessors(chain = true)
@TableName("ec_role_info")
public class RoleInfo extends LavaDo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    @TableField("role_name")
            private String roleName;
    /**
     * 角色code
     */
    @TableField("role_code")
            private String roleCode;


}