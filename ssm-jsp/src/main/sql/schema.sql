-- 数据库初始化脚本

-- 创建数据库
create database seckill;

-- 使用数据库
use seckill;

-- 创建秒杀库存表
-- 低版本的mysql数据库不支持
# 错误代码： 1293
# Incorrect table definition; there can be only one TIMESTAMP column with CURRENT_TIMESTAMP in DEFAULT or ON UPDATE clause
# 原因: 两台服务器的mysql版本不一致. 低版本不支持在一个表里面 有2个TIMESTAMP 类型  的列.
# 所以换成datetime
CREATE TABLE seckill(
  seckill_id BIGINT NOT NULL  AUTO_INCREMENT COMMENT '商品库存id',
  name VARCHAR(120) NOT NULL  COMMENT  '商品名称',
  number int NOT NULL  COMMENT '库存数量',
#   start_time DATETIME  NOT NULL COMMENT '秒杀开启时间',
#   end_time DATETIME NOT NULL COMMENT '秒杀结束时间',
#   create_time DATETIME NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  start_time TIMESTAMP NOT NULL COMMENT '秒杀开启时间',
  end_time TIMESTAMP NOT NULL COMMENT '秒杀结束时间',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  PRIMARY KEY(seckill_id),
  KEY idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)

)Engine=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET =utf8 COMMENT ='秒杀库存表';

-- 初始化数据
insert into
  seckill(name,number,start_time,end_time)
  values
('1000元秒杀iphone6',100,'2015-11-01 00:00:00','2015-11-01 00:00:00'),
('500元秒杀ipad2',200,'2015-11-01 00:00:00','2015-11-01 00:00:00'),
('300元秒杀小米4',300,'2015-11-01 00:00:00','2015-11-01 00:00:00'),
('200元秒杀红米note',400,'2015-11-01 00:00:00','2015-11-01 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关的信息
CREATE TABLE success_killed(
  seckill_id BIGINT NOT NULL  COMMENT '秒杀商品id',
  user_phone BIGINT NOT NULL  COMMENT '用户手机号',
  state TINYINT NOT NULL DEFAULT -1 COMMENT '-1/无效；0/成功；1/已付款；',
  create_time TIMESTAMP NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
  PRIMARY KEY(seckill_id,user_phone),/*联合主键，防止用户重复秒杀*/
  key idx_create_time(create_time)
)Engine=InnoDB DEFAULT  CHARSET =utf8 COMMENT ='秒杀成功明细表';

-- 链接数据库控制台
# mysql -uroot -p

-- 为什么手写ddl
-- 记录每次上线的ddl修改
-- 上线V.1
# ALTER  TABLE seckill
# DROP INDEX idx_start_time
# add index idx_c_s(start_time,end_time);

-- 上线V.2
-- ddl