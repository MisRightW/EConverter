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
@TableName("ec_permission_info")
public class PermissionInfo extends LavaDo {

    private static final long serialVersionUID = 1L;

    /**
     * 权限名称
     */
    @TableField("perm_name")
            private String permName;
    /**
     * 权限类型
     */
    @TableField("perm_type")
            private String permType;
    /**
     * 权限路径
     */
    @TableField("perm_url")
            private String permUrl;
    /**
     * 权限图标
     */
    @TableField("perm_icon")
            private String permIcon;


}