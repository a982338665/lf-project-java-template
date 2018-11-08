/*
Navicat MySQL Data Transfer

Source Server         : a-192.168.1.230
Source Server Version : 50641
Source Host           : 192.168.1.230:3306
Source Database       : aseckill

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2018-10-11 16:11:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for s_user
-- ----------------------------
DROP TABLE IF EXISTS `s_user`;
CREATE TABLE `s_user` (
  `id` bigint(20) NOT NULL COMMENT '用户id 手机号码',
  `nickname` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL COMMENT 'Md5(Md5(pass明文+固定salt)+salt)',
  `salt` varchar(10) DEFAULT NULL,
  `head` varchar(28) DEFAULT NULL COMMENT '头像，云存储id',
  `register_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `last_login_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登录时间',
  `login_count` int(11) DEFAULT '0' COMMENT '登陆次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_user
-- ----------------------------
INSERT INTO `s_user` VALUES ('17778004652', 'luofeng', 'b7797cce01b4b131b433b6acf4add449', '1a2b3c4d', null, '2018-10-11 09:04:19', '2018-10-11 09:04:23', '1');
