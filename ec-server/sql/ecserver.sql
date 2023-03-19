use ecserver;

# 用户表
DROP TABLE IF EXISTS `ec_user_info`;
CREATE TABLE `ec_user_info`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `gmt_create`   timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`      varchar(32)  NOT NULL COMMENT '创建人',
    `modifier`     varchar(32)  NOT NULL COMMENT '修改人',
    `deleted`      int(1)       NOT NULL DEFAULT 0 COMMENT '是否删除 [0:没有删除;1:已经删除;默认为0]',
    `user_name`    varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名',
    `user_pass`    varchar(32)  NOT NULL DEFAULT '' COMMENT '用户密码',
    `phone`        varchar(11)           DEFAULT NULL COMMENT '用户手机号',
    `role_id`      bigint(20)            DEFAULT NULL COMMENT '用户角色id',
    `nickname`     varchar(255) NOT NULL DEFAULT '' COMMENT '昵称',
    `avatar`       varchar(255) NOT NULL DEFAULT '' COMMENT '头像地址',
    `appid`        varchar(255) NOT NULL DEFAULT '' COMMENT '小程序id',
    `openid`       varchar(255) NOT NULL DEFAULT '' COMMENT '微信用户openid',
    `unionid`      varchar(255) NOT NULL DEFAULT '' COMMENT '微信用户unionid',
    `session_key`  varchar(255) NOT NULL DEFAULT '' COMMENT '登录后session_key',
    `access_token` varchar(255) NOT NULL DEFAULT '' COMMENT '服务端token',
    `user_id`      bigint(20) unsigned   DEFAULT NULL COMMENT '手机授权user',
    PRIMARY KEY (`id`),
    UNIQUE KEY (`phone`),
    UNIQUE KEY `openid` (`openid`, `appid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

# 权限表
DROP TABLE IF EXISTS `ec_permission_info`;
CREATE TABLE `ec_permission_info`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT COMMENT '权限id',
    `gmt_create`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`      varchar(32) NOT NULL COMMENT '创建人',
    `modifier`     varchar(32) NOT NULL COMMENT '修改人',
    `deleted`      int(1)      NOT NULL DEFAULT 0 COMMENT '是否删除 [0:没有删除;1:已经删除;默认为0]',
    `perm_name`    varchar(32)          DEFAULT NULL COMMENT '权限名称',
    `perm_type`    varchar(32)          DEFAULT NULL COMMENT '权限类型',
    `perm_url`     varchar(100)         DEFAULT NULL COMMENT '权限路径',
    `perm_icon`    varchar(64)          DEFAULT NULL COMMENT '权限图标',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

# 角色表
DROP TABLE IF EXISTS `ec_role_info`;
CREATE TABLE `ec_role_info`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `gmt_create`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`      varchar(32) NOT NULL COMMENT '创建人',
    `modifier`     varchar(32) NOT NULL COMMENT '修改人',
    `deleted`      int(1)      NOT NULL DEFAULT 0 COMMENT '是否删除 [0:没有删除;1:已经删除;默认为0]',
    `role_name`    varchar(32)          DEFAULT NULL COMMENT '角色名称',
    `role_code`    varchar(10)          DEFAULT NULL COMMENT '角色code',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

# 角色权限映射表
DROP TABLE IF EXISTS `ec_role_perm`;
CREATE TABLE `ec_role_perm`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT COMMENT '权限角色对应id',
    `gmt_create`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`      varchar(32) NOT NULL COMMENT '创建人',
    `modifier`     varchar(32) NOT NULL COMMENT '修改人',
    `deleted`      int(1)      NOT NULL DEFAULT 0 COMMENT '是否删除 [0:没有删除;1:已经删除;默认为0]',
    `role_id`      bigint               DEFAULT NULL COMMENT '角色id',
    `perm_id`      bigint               DEFAULT NULL COMMENT '权限id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

# 文件表
DROP TABLE IF EXISTS `ec_file_info`;
CREATE TABLE `ec_file_info`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT COMMENT '文件主键',
    `gmt_create`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`      varchar(32) NOT NULL COMMENT '创建人',
    `modifier`     varchar(32) NOT NULL COMMENT '修改人',
    `deleted`      int(1)      NOT NULL DEFAULT 0 COMMENT '是否删除 [0:没有删除;1:已经删除;默认为0]',
    `file_name`    varchar(64)          DEFAULT NULL COMMENT '文件名',
    `file_path`    varchar(128)         DEFAULT NULL COMMENT '文件路径',
    `file_size`    varchar(20)          DEFAULT NULL COMMENT '文件大小',
    `file_ext`     varchar(20)          DEFAULT NULL COMMENT '文件格式',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

# 转换任务表
DROP TABLE IF EXISTS `ec_change_task`;
CREATE TABLE `ec_change_task`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT COMMENT '文件转换任务id主键',
    `gmt_create`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `gmt_modified` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`      varchar(32) NOT NULL COMMENT '创建人',
    `modifier`     varchar(32) NOT NULL COMMENT '修改人',
    `deleted`      int(1)      NOT NULL DEFAULT 0 COMMENT '是否删除 [0:没有删除;1:已经删除;默认为0]',
    `task_no`      varchar(32)          DEFAULT NULL COMMENT '转换任务编号',
    `task_param`   varchar(256)         DEFAULT NULL COMMENT '任务参数',
    `status`       varchar(20)          DEFAULT NULL COMMENT '任务状态[0:任务初始化;1:任务完成;2:任务异常后重试;3:任务失败;]',
    `task_message` varchar(20)          DEFAULT NULL COMMENT '任务消息',
    `task_count`   int(2)               DEFAULT NULL COMMENT '任务执行次数',
    `next_time`    timestamp            DEFAULT NULL COMMENT '下一次执行时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;