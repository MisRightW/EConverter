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
@TableName("ec_role_perm")
public class RolePerm extends LavaDo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableField("role_id")
            private Long roleId;
    /**
     * 权限id
     */
    @TableField("perm_id")
            private Long permId;


}