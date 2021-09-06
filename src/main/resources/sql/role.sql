/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : campusrecruitment

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2021-09-06 17:41:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(30) NOT NULL AUTO_INCREMENT,
  `role_type` varchar(40) DEFAULT NULL,
  `type_key` int(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '普通用户', '0');
INSERT INTO `role` VALUES ('2', '公司用户', '1');
INSERT INTO `role` VALUES ('3', '管理员', '2');
