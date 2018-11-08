/*
Navicat MySQL Data Transfer

Source Server         : a-192.168.1.230
Source Server Version : 50641
Source Host           : 192.168.1.230:3306
Source Database       : aseckill

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2018-10-11 17:09:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_goods_seckill
-- ----------------------------
DROP TABLE IF EXISTS `s_goods_seckill`;
CREATE TABLE `s_goods_seckill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀的商品表主键id',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `seckill_price` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '秒杀价格',
  `seckill_stock` int(11) DEFAULT '0' COMMENT '商品库存数量 -1表示无限制',
  `start_time` datetime DEFAULT NULL COMMENT '商品详情介绍',
  `end_time` datetime DEFAULT NULL COMMENT '商品详情介绍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_goods_seckill
-- ----------------------------
INSERT INTO `s_goods_seckill` VALUES ('1', '1', '0.01', '100', '2018-10-11 17:08:12', '2018-10-20 17:08:16');
INSERT INTO `s_goods_seckill` VALUES ('2', '2', '0.02', '1000', '2018-10-12 17:09:06', '2018-10-19 17:09:12');
