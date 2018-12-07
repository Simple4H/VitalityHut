-- 作者author
-- number
-- default null
-- member --> user
-- messges -->message
create database db_vitalityhut character set utf8;

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

insert into simple_user VALUES(NULL,'simple','123123123','crescentcxm@gmail.com','18578230461','没有bug','是的',1,'2017-12-02 12:07:00', '2017-12-02 12:07:01');
insert into simple_user VALUES(NULL,'huangbude','123123123','673724357@gmail.com','18666981323','好不好','好',0,'2017-12-02 12:07:00', '2017-12-02 12:07:01');

INSERT into simple_message VALUES (NULL ,'消息测试1','这是消息测试1','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'消息测试2','这是消息测试2','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'消息测试3','这是消息测试3','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'宿舍1','大家好,我是,林胜涵','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'宿舍2','林胜涵请大家吃饭','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'宿舍3','林胜涵秀不动了','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'无敌','林胜涵就是传说中的宿友','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'你好','知道什么是无敌嘛?我是30号,叶树梁','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'叶树梁','兄弟,我是无敌的','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'你好','啊啊实打实的','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'啊实打实大苏打撒','现场捐款v赫鲁晓夫尽快v和','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_message VALUES (NULL ,'把这个vi涉猎广泛','ekjhagfbvkjs.dBFVJKL>SDBugfk ','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');



INSERT into simple_group VALUES (NULL ,'小组测试1','这是小组1','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_group VALUES (NULL ,'小组测试2','这是小组2','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_group VALUES (NULL ,'小组测试3','这是小组3','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_group VALUES (NULL ,'小组测试4','这是小组4','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_group VALUES (NULL ,'小组测试5','这是小组5','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');
INSERT into simple_group VALUES (NULL ,'小组测试6','这是小组6','simple','2017-12-13 21:54:00','2017-12-13 21:54:00');

