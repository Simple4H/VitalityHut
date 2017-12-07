-- 作者author
CREATE DATABASE `db_vitalityhut`;

USE db_vitalityhut;

CREATE TABLE `simple_user` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT
  COMMENT '用户表id',
  `username`    VARCHAR(50) NOT NULL
  COMMENT '用户名',
  `password`    VARCHAR(50) NOT NULL
  COMMENT '用户密码',
  `email`       VARCHAR(50)          DEFAULT NULL
  COMMENT '邮箱',
  `phone`       VARCHAR(20)          DEFAULT NULL
  COMMENT '手机',
  `question`    VARCHAR(100)         DEFAULT NULL
  COMMENT '找回密码问题',
  `answer`      VARCHAR(100)         DEFAULT NULL
  COMMENT '找回密码答案',
  `role`        INT(4)      NOT NULL
  COMMENT '角色0-管理员,1-普通用户',
  `create_time` DATETIME    NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME    NOT NULL
  COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `simple_message` (
  `id`          INT(11)       NOT NULL AUTO_INCREMENT
  COMMENT '消息表id',
  `title`       VARCHAR(100)  NOT NULL
  COMMENT '标题',
  `messges`     VARCHAR(1000) NOT NULL
  COMMENT '消息',
  `anthor`      VARCHAR(100)  NOT NULL
  COMMENT '作者',
  `create_time` DATETIME      NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME      NOT NULL
  COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title_unique` (`title`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `simple_group` (
  `id`          INT(11)       NOT NULL AUTO_INCREMENT
  COMMENT '小组id',
  `title`       VARCHAR(100)  NOT NULL
  COMMENT '组名',
  `messges`     VARCHAR(1000) NOT NULL
  COMMENT '组消息',
  `member`      VARCHAR(1000) NOT NULL
  COMMENT '组成员',
  `create_time` DATETIME      NOT NULL
  COMMENT '创建时间',
  `update_time` DATETIME      NOT NULL
  COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title_unique` (`title`) USING BTREE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

insert into simple_user VALUES(NULL,'simple','123123','crescentcxm@gmail.com','18578230461','没有bug','是的',1,'2017-12-02 12:07:00', '2017-12-02 12:07:01');
insert into simple_user VALUES(NULL,'huangbude','123123','673724357@gmail.com','18666981323','好不好','好',0,'2017-12-02 12:07:00', '2017-12-02 12:07:01');

