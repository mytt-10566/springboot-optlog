CREATE TABLE `opt_log`
(
  `id`          bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name`   varchar(32)         NOT NULL DEFAULT '' COMMENT '用户姓名',
  `opt`         varchar(256)        NOT NULL DEFAULT '' COMMENT '操作',
  `args`        text COMMENT '操作参数',
  `ip`          varchar(64)         NOT NULL DEFAULT '' COMMENT 'ip',
  `create_time` bigint(20)          NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(20)          NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_name` (`user_name`),
  KEY `idx_opt` (`opt`),
  KEY `idx_create_time` (`create_time`)
) ENGINE = InnoDB COMMENT ='操作日志表';