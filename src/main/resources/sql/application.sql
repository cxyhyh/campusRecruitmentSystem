/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : campusrecruitment

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2021-09-06 17:41:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for application
-- ----------------------------
DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `application_id` int(40) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `role_type` varchar(20) DEFAULT NULL,
  `application_position` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`application_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of application
-- ----------------------------
