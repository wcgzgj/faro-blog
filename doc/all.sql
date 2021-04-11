# 测试表
drop table if exists `test`;
create table `test` (
  `id` bigint not null comment 'id',
  `name` varchar(50) comment '名称',
  `password` varchar(50) comment '密码',
  primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='测试';

insert into `test` (id, name, password) values (1, '测试', 'password');


# demo列表
drop table if exists `demo`;
create table `demo`
(
    `id`   bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment '测试';

insert into `demo` (id, name)
values (2, '测试2');

-- 用户表
drop table if exists `user`;
create table `user` (
                        `id` bigint not null comment 'ID',
                        `login_name` varchar(50) not null comment '登陆名',
                        `name` varchar(50) comment '昵称',
                        `password` char(32) not null comment '密码',
                        primary key (`id`),

                        # 用户名设置唯一键
                        unique key `login_name_unique` (`login_name`)
) engine=innodb default charset=utf8mb4 comment='用户';

insert into `user` (id, `login_name`, `name`, `password`) values (1, 'test', '测试', 'e70e2222a9d67c4f2eae107533359aa4');
insert into `user` (id, `login_name`, `name`, `password`) values (2, 'test2', '测试2', '2');


-- 文档内容
drop table if exists `content`;
create table `content` (
                           `id` bigint not null comment '文档id',
                           `content` mediumtext not null comment '内容',
                           primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='文档内容';


# 博客列表
drop table if exists `doc`;
create table `doc` (
                         `id` bigint not null comment 'id',
                         `name` varchar(50) comment '名称',
                         `category1_id` bigint comment '分类1',
                         `category2_id` bigint comment '分类2',
                         `description` varchar(200) comment '描述',
                         `cover` varchar(200) comment '封面',
                         `doc_count` int not null default 0 comment '文档数',
                         `view_count` int not null default 0 comment '阅读数',
                         `vote_count` int not null default 0 comment '点赞数',
                         primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='电子书';

insert into `doc` (id, name, description) values (1, 'Spring Boot 入门教程', '零基础入门 Java 开发，企业级应用开发最佳首选框架');
insert into `doc` (id, name, description) values (2, 'Vue 入门教程', '零基础入门 Vue 开发，企业级应用开发最佳首选框架');
insert into `doc` (id, name, description) values (3, 'Python 入门教程', '零基础入门 Python 开发，企业级应用开发最佳首选框架');
insert into `doc` (id, name, description) values (4, 'Mysql 入门教程', '零基础入门 Mysql 开发，企业级应用开发最佳首选框架');
insert into `doc` (id, name, description) values (5, 'Oracle 入门教程', '零基础入门 Oracle 开发，企业级应用开发最佳首选框架');

