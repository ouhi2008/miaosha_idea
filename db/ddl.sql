--用户表
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



--商品表
create table goods(
id bigint(20) not null auto_increment comment '商品ID',
goods_name varchar(16) not null,
goods_title varchar(64) default null comment '商品名称',
goods_img varchar(64) default null,
goods_detail longtext ,
goods_price decimal(10,2) default 0.00 ,
goods_stock int(11) default 0,
primary key (id)
) engine=InnoDB default charset=utf8mb4;

insert into goods values(1,'iphoneX','Apple iPhoneX','/img/iphonex.png','Apple iPhone X (a1865) 64G 银色',8000,100);
insert into goods values(2,'Meta10','HuaWei Meta10','/img/meta10.png','HuaWei Meta10 64G 黑色',3000,100);


--秒杀商品表
create table miaosha_goods(
id bigint(20) not null auto_increment comment 'miaosha商品ID',
goods_id bigint(20) not null comment '商品ID',
miaosha_price decimal(10,2) default 0.00 ,
stock_count int(11) default null,
start_date  datetime default null,
end_date  datetime default null,
primary key (id)
) engine=InnoDB default charset=utf8mb4;

insert into miaosha_goods values(1,1,0.01,4,'2018-07-30 14:00:00','2018-07-31 14:00:00'),(2,2,0.01,9,'2018-07-30 14:00:00','2018-07-31 14:00:00');



--订单表
create table order_info(
id bigint(20) not null auto_increment comment '订单ID',
user_id bigint(20) not null,
goods_id bigint(20) default null comment '商品id',
delivery_addr_id bigint(20) default null,
goods_name varchar(16) ,
goods_count int(11) default 0 ,
goods_price decimal(10,2) default 0.00 ,
order_channel tinyint(4) default 0,
status tinyint(4) efault 0,
create_date datetime default null,
pay_date datetime default null,
primary key(id)
) engine=InnoDB auto_increment=12 default charset=utf8mb4;



--秒杀订单表
create table miaosha_order(
id bigint(20) not null auto_increment comment '订单ID',
user_id bigint(20) not null,
order_id bigint(20) not null,
goods_id bigint(20) default null comment '商品id',
primary key(id)
) engine=InnoDB auto_increment=12 default charset=utf8mb4;
