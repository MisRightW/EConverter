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
@TableName("ec_user_info")
public class UserInfo extends LavaDo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("user_name")
            private String userName;
    /**
     * 用户密码
     */
    @TableField("user_pass")
            private String userPass;
    /**
     * 用户手机号
     */
    @TableField("phone")
            private String phone;
    /**
     * 用户角色id
     */
    @TableField("role_id")
            private Long roleId;
    /**
     * 昵称
     */
    @TableField("nickname")
            private String nickname;
    /**
     * 头像地址
     */
    @TableField("avatar")
            private String avatar;
    /**
     * 小程序id
     */
    @TableField("appid")
            private String appid;
    /**
     * 微信用户openid
     */
    @TableField("openid")
            private String openid;
    /**
     * 微信用户unionid
     */
    @TableField("unionid")
            private String unionid;
    /**
     * 登录后session_key
     */
    @TableField("session_key")
            private String sessionKey;
    /**
     * 服务端token
     */
    @TableField("access_token")
            private String accessToken;
    /**
     * 手机授权user
     */
    @TableField("user_id")
            private Long userId;


}