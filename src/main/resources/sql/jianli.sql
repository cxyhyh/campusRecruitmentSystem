/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : campusrecruitment

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2021-09-06 17:41:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jianli
-- ----------------------------
DROP TABLE IF EXISTS `jianli`;
CREATE TABLE `jianli` (
  `jianli_id` int(100) NOT NULL AUTO_INCREMENT,
  `file_url` varchar(100) DEFAULT NULL,
  `file_name` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `application_position` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`jianli_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of jianli
-- ----------------------------
INSERT INTO `jianli` VALUES ('36', 'F:\\upload', '毕业设计说明书.doc', 'cm', '运维工程师');
INSERT INTO `jianli` VALUES ('38', 'F:\\upload', '普通用户.jpg', 'wq', '药品注册');
INSERT INTO `jianli` VALUES ('39', 'F:\\upload', '开题报告-1713011451-韩宇豪-15534033571.doc', 'btf', '大数据分析师');
INSERT INTO `jianli` VALUES ('40', 'F:\\upload', '毕业设计说明书-1713011451-韩宇豪.doc', 'wq', '电信网络工程师');
INSERT INTO `jianli` VALUES ('41', 'F:\\upload', '毕业设计说明书-1713011451-韩宇豪.doc', 'wq', '电信网络工程师');
INSERT INTO `jianli` VALUES ('42', 'F:\\upload', '开题报告-1713011451-韩宇豪-15534033571.doc', 'wq', '资产分析师');
INSERT INTO `jianli` VALUES ('46', 'F:\\upload', '校园招聘管理系统设计与实现__.doc', 'wq', '食品鉴赏师');
INSERT INTO `jianli` VALUES ('47', 'F:\\upload', '开题报告-1713011451-韩宇豪-15534033571.doc', 'wq', '食品鉴赏师');
INSERT INTO `jianli` VALUES ('48', 'F:\\upload', '毕业设计说明书-1713011451-韩宇豪.doc', 'wq', '食品鉴赏师');
INSERT INTO `jianli` VALUES ('49', 'F:\\upload', '普通用户.jpg', 'wq', '食品鉴赏师');
INSERT INTO `jianli` VALUES ('50', 'F:\\upload', '开题报告-1713011451-韩宇豪-15534033571.doc', 'wq', '食品鉴赏师');
INSERT INTO `jianli` VALUES ('51', 'F:\\upload', '系统流程图.jpg', 'wq', '食品鉴赏师');
INSERT INTO `jianli` VALUES ('52', 'F:\\upload', '管理员.jpg', 'wq', '食品鉴赏师');
INSERT INTO `jianli` VALUES ('53', 'F:\\upload', '毕业设计说明书封皮示例（张鹏跃）.doc', 'hyh', '运维工程师');
INSERT INTO `jianli` VALUES ('54', 'F:\\upload', '韩宇豪-出勤天数.txt', 'wq', 'JAVA开发工程师');
INSERT INTO `jianli` VALUES ('55', 'F:\\upload', 'fcc86da22b5b0ae2787c2a16715941a.png', 'xh', '运维工程师');
INSERT INTO `jianli` VALUES ('56', 'F:\\upload', 'fcc86da22b5b0ae2787c2a16715941a.png', 'xh', '运维工程师');
