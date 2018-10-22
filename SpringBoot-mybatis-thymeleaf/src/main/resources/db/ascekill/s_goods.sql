/*
Navicat MySQL Data Transfer

Source Server         : a-192.168.1.230
Source Server Version : 50641
Source Host           : 192.168.1.230:3306
Source Database       : aseckill

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2018-10-11 17:00:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_goods
-- ----------------------------
DROP TABLE IF EXISTS `s_goods`;
CREATE TABLE `s_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(16) DEFAULT NULL COMMENT '商品名称',
  `goods_title` varchar(64) DEFAULT NULL COMMENT '商品标题',
  `goods_img` varchar(64) DEFAULT NULL COMMENT '商品图片',
  `goods_detail` longtext COMMENT '商品详情介绍',
  `goods_price` decimal(10,2) unsigned DEFAULT '0.00' COMMENT '商品单价',
  `goods_stock` int(11) DEFAULT '0' COMMENT '商品库存 -1表示无限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_goods
-- ----------------------------
INSERT INTO `s_goods` VALUES ('1', 'spring源码剖析', '标题...', 'img', 'spring详情展示...', '10.05', '100');
INSERT INTO `s_goods` VALUES ('2', 'java基础', 'Java基础标题', 'java-img', 'java详情展示...', '0.00', '100');
