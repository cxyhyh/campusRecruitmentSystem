/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : campusrecruitment

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2021-09-06 17:41:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for skill
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill` (
  `skill_id` int(10) NOT NULL AUTO_INCREMENT,
  `applied_position` varchar(100) DEFAULT NULL,
  `english_skill` varchar(50) DEFAULT NULL,
  `computer_skill` varchar(50) DEFAULT NULL,
  `main_skill` varchar(300) DEFAULT NULL,
  `real_name` varchar(40) DEFAULT NULL,
  `username` varchar(40) NOT NULL,
  PRIMARY KEY (`skill_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of skill
-- ----------------------------
INSERT INTO `skill` VALUES ('1', '测试工程师', '六级', '二级', '掌握测试技术', '王强', 'wq');
INSERT INTO `skill` VALUES ('2', '产品经理', '六级', '四级', '沟通能力强，熟悉产品研发', '肖红', 'xh');
INSERT INTO `skill` VALUES ('3', '大数据分析师', '六级', '二级', '熟悉数据库编程，熟悉统计分析，熟悉数据挖掘，python，可视化以及Hadoop', '蔡明', 'cm');
INSERT INTO `skill` VALUES ('4', 'JAVA开发工程师', '六级', '二级', '熟练使用MySQL、Oracle数据库，熟练Java语法，spring框架', '宋一名', 'sym');
INSERT INTO `skill` VALUES ('5', '运维工程师', '四级', '二级', '熟练使用Linux命令，熟悉TCP/协议IP，熟练使用虚拟机及其相关配置', '韩庚', 'hg');
INSERT INTO `skill` VALUES ('6', '市场营销', '四级', '二级', '口才能力好，交际能力强，工作积极，为人热情', '姚臣', 'yc');
INSERT INTO `skill` VALUES ('7', '幼师', '四级', '二级', '性格好，喜欢小孩，很会照顾小孩子', '马新栋', 'mxd');
INSERT INTO `skill` VALUES ('8', '系统架构设计师', '四级', '二级', '熟希各种底层框架源码，熟悉使用多种数据库', '边腾飞', 'btf');
INSERT INTO `skill` VALUES ('9', '医药销售主管', '四级', '二级', '口才能力好，熟悉各种药物专业知识，业务能力强', '邱琳琳', 'qll');
INSERT INTO `skill` VALUES ('10', '产品经理', '四级', '二级', '交际能力强，业务能力一流', '王萌', 'wm');
INSERT INTO `skill` VALUES ('11', '食品鉴赏师', '四级', '二级', '对食品鉴赏有自己独特的见解', '张婷', 'zt');
INSERT INTO `skill` VALUES ('12', '大数据产品经理', '四级', '二级', '口才能力好，交际能力强', '张小娟', 'zxj');
INSERT INTO `skill` VALUES ('13', '制药工程师', '四级', '二级', '专业知识能力过硬，对制药的一系列流程熟悉', '高耀', 'gy');
INSERT INTO `skill` VALUES ('14', '汽车项目管理', '四级', '二级', '熟悉汽车生产加工流程', '郭林川', 'glc');
INSERT INTO `skill` VALUES ('15', '房地产项目开发工程师', '四级', '二级', '交际能力强，口才好，房地产知识过硬', '潘长姜', 'pcj');
INSERT INTO `skill` VALUES ('16', '大数据分析师', '四级', '二级', '熟悉数据库编程，熟悉统计分析，熟悉数据挖掘，python，可视化以及Hadoop', '李易峰', 'lyf');
INSERT INTO `skill` VALUES ('17', '银行客户经理', '四级', '二级', '口才能力好，交际能力强', '梁小与', 'lxy');
INSERT INTO `skill` VALUES ('18', '房地产销售经理', '四级', '二级', '交际能力强，业务能力一流', '马露', 'ml');
INSERT INTO `skill` VALUES ('19', '汽车设计工程师', '四级', '二级', '交际能力强，业务能力一流', '张晨', 'zc');
INSERT INTO `skill` VALUES ('20', '汽车美容师 ', '四级', '二级', '肯吃苦，工作认真，态度积极', '谢霆锋', 'xtf');
INSERT INTO `skill` VALUES ('21', 'C语言开发工程师', '四级', '二级', '熟练使用C语言编程', '丁淼', 'dm');
INSERT INTO `skill` VALUES ('22', '电信网络工程师', '四级', '二级', '熟悉TCP/协议IP，熟练使用虚拟机及其相关配置', '刘奔于', 'lby');
INSERT INTO `skill` VALUES ('23', '客户专员', '四级', '二级', '交际能力强，业务能力一流', '王华', 'wh');
INSERT INTO `skill` VALUES ('24', '测试工程师', '六级', '二级', '牛', '韩宇豪', 'hyh');
INSERT INTO `skill` VALUES ('30', null, null, null, null, null, 'lb');
