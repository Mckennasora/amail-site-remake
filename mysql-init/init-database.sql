create database if not exists mail_site_remake;

use mail_site_remake;

create table if not exists site_user
(
    id            varchar(256)                       not null primary key,
    username      varchar(256)                       not null comment '账号',
    password      varchar(256)                       not null comment '密码',
    user_nickname varchar(256)                       null comment '账号',
    gender        varchar(10)                        null comment '性别',
    user_email    varchar(512)                       null comment '邮箱',
    user_phone    varchar(128)                       null comment '电话',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     varchar(256)                       null comment '创建人',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    update_by     varchar(256)                       null comment '更新人',
    is_deleted    tinyint  default 0                 null comment '是否删除'
)
    comment '用户表';

create table site_role
(
    id                varchar(256)                       not null
        primary key,
    role_name          varchar(256)                       not null comment '角色名',
    create_time       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         varchar(256)                       null comment '创建人',
    update_time       datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    update_by         varchar(256)                       null comment '更新人',
    is_deleted         tinyint  default 0                 null comment '是否删除'
)
    comment '角色表';


create table if not exists site_user_role
(
    id         varchar(256)                       not null
        primary key,
    user_id     varchar(256)                       not null comment '用户Id',
    role_id     varchar(256)                       not null comment '角色Id',
    create_time       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         varchar(256)                       null comment '创建人',
    update_time       datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    update_by         varchar(256)                       null comment '更新人',
    is_deleted  tinyint  default 0                 null comment '是否删除'
)
    comment '用户角色表';


create table if not exists site_mail_plan
(
    id               varchar(256)                       not null
        primary key,
    userId           varchar(256)                       not null comment '用户Id',
    arrSysScheduleId varchar(2048)                      null comment '系统时刻表ids',
    arrDIYScheduleId varchar(2048)                      null comment '自建时刻表ids',
    toWho            varchar(256)                       not null comment '收件人email',
    subject          varchar(256)                       not null comment '邮件主题',
    mainBody         varchar(2048)                      null comment '正文',
    arrPhotoUrl      varchar(4096)                      null comment '图片地址',
    sendCount        int      default 0                 null comment '发送次数',
    remarks          varchar(256)                       null comment '备注',
    isEnable         tinyint  default 0                 null comment '是否开启',
    createTime       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime       datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDeleted        tinyint  default 0                 null comment '是否删除'
)
    comment '计划表';


create table if not exists site_mail_cron
(
    id         varchar(256)                       not null
        primary key,
    userId     varchar(256)                       not null comment '用户Id',
    cronExpr   varchar(128)                       null comment 'cron表达式',
    useCount   int      default 0                 null comment '发送次数',
    remarks    varchar(256)                       null comment '备注',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDeleted  tinyint  default 0                 null comment '是否删除'
)
    comment 'cron表达式';


create table if not exists site_mail_history
(
    id               varchar(256)                       not null
        primary key,
    userId           varchar(256)                       not null comment '用户Id',
    mailPlanId       varchar(256)                       not null comment '计划Id',
    arrSysScheduleId varchar(2048)                      null comment '系统时刻表ids',
    arrDIYScheduleId varchar(2048)                      null comment '自建时刻表ids',
    sendByCronExpr   varchar(2048)                      null comment 'cron表达式',
    sendByCronExprId varchar(2048)                      null comment 'cron表达式id',
    toWho            varchar(256)                       not null comment '收件人email',
    subject          varchar(256)                       not null comment '邮件主题',
    mainBody         varchar(2048)                      null comment '正文',
    arrPhotoUrl      varchar(4096)                      null comment '图片地址',
    tryCount         tinyint  default 0                 null comment '尝试次数',
    remarks          varchar(256)                       null comment '备注',
    isSuccess        tinyint  default 0                 null comment '是否成功',
    createTime       datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime       datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    isDeleted        tinyint  default 0                 null comment '是否删除'
)
    comment '历史记录';
