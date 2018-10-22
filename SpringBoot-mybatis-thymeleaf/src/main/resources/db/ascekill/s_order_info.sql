/*
Navicat MySQL Data Transfer

Source Server         : a-192.168.1.230
Source Server Version : 50641
Source Host           : 192.168.1.230:3306
Source Database       : aseckill

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2018-10-11 17:22:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_order_info
-- ----------------------------
DROP TABLE IF EXISTS `s_order_info`;
CREATE TABLE `s_order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `delivery_add_id` bigint(20) DEFAULT NULL COMMENT '收货地址id',
  `goods_name` varchar(16) DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` int(11) DEFAULT '0' COMMENT '商品下单数量',
  `goods_price` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '商品单价',
  `order_channel` tinyint(4) DEFAULT '0' COMMENT '1-pc 2-android 3-ios',
  `seckill_price` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '秒杀价格',
  `STATUS` tinyint(4) DEFAULT '0' COMMENT '订单状态：0-未支付 1-已支付 2-未发货 3-已发货 4-已收货 5.已退款 6-已完成 ',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='订单基础信息表';

-- ----------------------------
-- Records of s_order_info
-- ----------------------------
