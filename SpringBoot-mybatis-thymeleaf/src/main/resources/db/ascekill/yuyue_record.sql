/*
Navicat MySQL Data Transfer

Source Server         : 114.55.214.122online
Source Server Version : 50634
Source Host           : 114.55.214.122:3306
Source Database       : skk_srv_order_online

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2018-10-18 15:15:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for yuyue_record
-- ----------------------------
DROP TABLE IF EXISTS `yuyue_record`;
CREATE TABLE `yuyue_record` (
  `预约类型` varchar(4) DEFAULT NULL,
  `医生姓名` varchar(50) NOT NULL COMMENT '预约医生姓名',
  `订单号` varchar(50) NOT NULL COMMENT '订单号',
  `患者姓名` varchar(50) NOT NULL COMMENT '就诊人姓名',
  `患者性别` varchar(2) DEFAULT NULL,
  `患者年龄` tinyint(4) unsigned COMMENT '年龄',
  `患者联系电话` varchar(50) COMMENT '联系电话',
  `患者所患疾病` varchar(50) COMMENT '所患疾病',
  `患者病情描述` text,
  `预约时间` datetime NOT NULL COMMENT '创建时间',
  KEY `time_idx` (`预约时间`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of yuyue_record
-- ----------------------------
INSERT INTO `yuyue_record` VALUES ('远程门诊', '周蓉', '500945838613209088', '张雨霞', '女', '37', '13519046167', '异常出血', '经常出血，偶尔肚子会疼', '2018-10-14 08:19:55');
INSERT INTO `yuyue_record` VALUES ('远程门诊', '周蓉', '501356664973168640', '马旭娟', '女', '30', '18993437717', '尿频尿急', '小腹胀痛伴有腰疼谢谢谢谢', '2018-10-15 11:32:24');
INSERT INTO `yuyue_record` VALUES ('面诊预约', '周蓉', '502449707813965824', '陈红梅', '女', '28', '13689345553', '月经不调，', '月经量少，肚子疼腰疼', '2018-10-18 11:55:45');
INSERT INTO `yuyue_record` VALUES ('面诊预约', '燕鑫', '502464931954692096', '郑讨琴', '女', '43', '15193646717', '宫颈炎', '肚子疼，腰底酸痛，妇科炎症', '2018-10-18 12:56:15');
INSERT INTO `yuyue_record` VALUES ('面诊预约', '闫震', '501425206846300160', '王佳琦', '女', '24', '13993421452', '卵巢囊肿', '大姨妈前后肚子抽疼，大姨妈前后出虚汗，月经前后瘙痒', '2018-10-15 16:04:45');
INSERT INTO `yuyue_record` VALUES ('面诊预约', '闫震', '501861888427036672', '张彐平', '女', '51', '15719657160', 'CIN2级', '需要宫颈锥切，青霉素过敏', '2018-10-16 20:59:58');
INSERT INTO `yuyue_record` VALUES ('面诊预约', '闫震', '501870600600428544', '郝兆莲', '女', '48', '15101841237', '子宫肌瘤', '子宫肌瘤前三年做过取病手术一年前检查复发', '2018-10-16 21:34:35');
INSERT INTO `yuyue_record` VALUES ('面诊预约', '闫震', '502173001001738240', '念宏图', '男', '50', '18910237136', '腰疼', '我，试一下能否预约？手术和面诊都可以预约', '2018-10-17 17:36:13');
INSERT INTO `yuyue_record` VALUES ('面诊预约', '闫震', '502249686032191488', '宫慧', '女', '34', '18009346298', '妇科良性疾病', '下腹疼，白带多，腰疼', '2018-10-17 22:40:56');
INSERT INTO `yuyue_record` VALUES ('面诊预约', '闫震', '502465431081066496', '张粉花', '女', '42', '17326418987', '盆腔炎', '腰疼小肚子疼在医院做过囊肿手术但效果不好', '2018-10-18 12:58:14');
INSERT INTO `yuyue_record` VALUES ('面诊预约', '闫震', '502488004116357120', '魏粉琴', '女', '58', '14719691222', '子宫瘤', 'B超查出有子宫瘤大小为6.2', '2018-10-18 14:27:56');
INSERT INTO `yuyue_record` VALUES ('手术预约', '闫震', '500937659389054976', '王俊梅', '女', '76', '13993430124', '子宫脱坠', '具体病情也不知道，要检查后才知道', '2018-10-14 07:47:25');
INSERT INTO `yuyue_record` VALUES ('手术预约', '闫震', '501315734681423872', '李林芝', '女', '44', '13884176639', '妇科', '流血时间较长，未找到原因！', '2018-10-15 08:49:45');
INSERT INTO `yuyue_record` VALUES ('手术预约', '闫震', '501424774807818240', '王佳琦', '女', '24', '13993421452', '卵巢囊肿', '大姨妈前后肚子抽疼，大姨妈前出虚汗', '2018-10-15 16:03:02');
INSERT INTO `yuyue_record` VALUES ('手术预约', '闫震', '501878837617893376', '郝兆莲', '女', '48', '15101841237', '子宫肌瘤', '子宫肌瘤前三年取病现在复发', '2018-10-16 22:07:19');
