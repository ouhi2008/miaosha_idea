create table miaosha_user(
id bigint(20) not null comment '用户ID，手机号码',
nickname varchar(255) not null,
password varchar(32) default null comment 'md5(md5(pass+salt)+salt)',
salt varchar(10) default null,
head varchar(128) default null comment 'head picture id',
register_date datetime default null ,
last_login_date datetime default null,
login_count int(11) default '0',
primary key (id)
) engine=InnoDB default charset=utf8mb4;