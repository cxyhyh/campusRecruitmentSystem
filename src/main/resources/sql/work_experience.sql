/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : campusrecruitment

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2021-09-06 17:41:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for work_experience
-- ----------------------------
DROP TABLE IF EXISTS `work_experience`;
CREATE TABLE `work_experience` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `entry_time` datetime DEFAULT NULL,
  `departure_time` datetime DEFAULT NULL,
  `work_company` varchar(200) DEFAULT NULL,
  `post` varchar(200) DEFAULT NULL,
  `duty` varchar(200) DEFAULT NULL,
  `username` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_experience
-- ----------------------------
INSERT INTO `work_experience` VALUES ('1', '2021-06-22 11:18:29', '2021-08-03 11:18:36', '华为', 'java开发', '1、参与完成不同类资产的六大模型的制定、测试和优化工作；\r\n2、参与完成资产管理的咨询类项目报告的部分撰写；\r\n3、参与完成精细测评报告资产、估值的部分完成；\r\n4、参与完成资管类的行业专题的部分研究。', 'wq');
INSERT INTO `work_experience` VALUES ('2', '2021-06-22 11:18:29', '2021-08-03 11:18:36', '百度', 'java开发', '4、有责任心，具有一定的团队管理能力和市场分析、规划能力。', 'wq');
INSERT INTO `work_experience` VALUES ('3', '2021-06-22 11:18:29', '2021-09-01 09:42:24', 'hua', 'java', '11', 'hyh');
INSERT INTO `work_experience` VALUES ('8', null, null, null, null, null, 'lb');
