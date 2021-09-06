/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : campusrecruitment

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2021-09-06 17:41:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for self_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `self_evaluation`;
CREATE TABLE `self_evaluation` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `self_evaluation` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of self_evaluation
-- ----------------------------
INSERT INTO `self_evaluation` VALUES ('1', 'wq', '1、根据需求定义软件系统功能，设计实现方案、编写设计文档；\r\n2、承担软件系统的开发、测试和调试工作；\r\n3、分析产品中出现的功能和性能缺陷，并提供解决方案；\r\n4、参与新技术预研和选型，设计合理的应用方案；\r\n5、完成上级安排其他各项技术任务。');
INSERT INTO `self_evaluation` VALUES ('2', 'hyh', '5、完成上级安排其他各项技术任务。');
INSERT INTO `self_evaluation` VALUES ('7', 'lb', null);
