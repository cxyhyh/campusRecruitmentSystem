/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : campusrecruitment

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2021-09-06 17:41:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `age` int(40) NOT NULL,
  `type_key` int(10) DEFAULT NULL,
  `real_name` varchar(40) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `idCard` varchar(18) DEFAULT NULL,
  `nature` varchar(10) DEFAULT NULL,
  `hometown` varchar(50) DEFAULT NULL,
  `education` varchar(50) DEFAULT NULL,
  `college` varchar(50) DEFAULT NULL,
  `mobilePhone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `regist_time` date DEFAULT NULL,
  `politics_status` varchar(40) DEFAULT NULL,
  `photo` text,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('153', 'wq', '1234567', '21', '0', '王强', '男', '1999-09-08', '142322199909083011', '汉', '北京市', '专科', '吕梁学院', '13383587557', '28601165651@qq.com', '山西省文水县', '2021-09-01', '中共党员', 'F:\\files\\fcc86da22b5b0ae2787c2a16715941a.png');
INSERT INTO `user` VALUES ('154', 'hyh', '123456', '21', '2', '韩宇豪', '男', '1999-09-07', '142322189009086701', '汉', '山西省吕梁市', '本科', '中北大学', '15534033571', '2860116561@qq.com', '北京市房山区', '2021-08-31', '中共预备党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('155', 'lxm', '123', '44', '1', '李晓明', '男', '1976-12-02', '150428197612021199', '汉', '山西省太原市', '硕士', '清华大学', '18502314567', '23456@qq.com', '北京市海淀区', '2021-09-01', '共青团员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('156', 'xh', '123456', '22', '0', '肖红', '女', '1999-07-16', '520203199907160823', '汉', '北京市朝阳区', '博士', '北京大学', '13456982345', '1247823567@qq.com', '北京市朝阳区', '2021-09-01', '中共预备党员', 'F:\\upload\\fcc86da22b5b0ae2787c2a16715941a.png');
INSERT INTO `user` VALUES ('157', 'cm', '123456', '22', '0', '蔡明', '男', '1999-01-06', '520203199901060813', '汉', '山西省太原市', '本科', '太原理工大学', '18928765432', 'cm@qq.com', '山西省太原市', '2021-09-01', '共青团员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('158', 'sym', '123456', '29', '0', '宋一名', '男', '1992-04-26', '220521199204268326', '汉', '北京市昌平区', '本科', '北京大学', '18828765432', 'sym@qq.com', '北京市昌平区', '2021-09-01', '民主党派', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('159', 'hg', '123456', '31', '0', '韩庚', '男', '1990-03-07', '11010119900307889X', '汉', '山西省太原市', '本科', '华南理工大学', '18938765432', 'hg@qq.com', '山西省太原市', '2021-09-01', '共青团员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('160', 'yc', '123456', '31', '0', '姚臣', '男', '1990-03-07', '110101199003079956', '汉', '山西省太原市', '本科', '西安交通大学', '18928565432', 'yc@qq.com', '山西省太原市', '2021-09-01', '中共预备党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('161', 'mxd', '123456', '31', '0', '马新栋', '男', '1990-03-07', '11010119900307299X', '汉', '北京市朝阳区', '本科', '北京理工大学', '18938765432', 'mxd@qq.com', '北京市朝阳区', '2021-09-01', '共青团员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('162', 'btf', '123456', '31', '0', '边腾飞', '男', '1990-03-07', '230882197209072375', '汉', '北京市海淀区', '本科', '北京航空航天大学', '18929765432', 'btf@qq.com', '北京市海淀区', '2021-09-01', '中共党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('163', 'qll', '123456', '23', '0', '邱琳琳', '女', '1998-08-09', '110101199003079913', '汉', '山西省太原市', '本科', '山西医科大学', '18908765432', 'qll@qq.com', '山西省太原市', '2021-09-01', '共青团员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('164', 'wm', '123456', '23', '0', '王萌', '女', '1998-08-09', '110101199003074573', '汉', '山西省太原市', '本科', '南京大学', '18928768432', 'zt@qq.com', '山西省太原市', '2021-09-01', '共青团员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('165', 'zt', '123456', '23', '0', '张婷', '女', '1998-08-09', '110101199003077299', '汉', '山西省太原市', '本科', '浙江大学', '18928762432', 'zt@qq.com', '山西省太原市', '2021-09-01', '中共党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('166', 'zxj', '123456', '23', '0', '张小娟', '女', '1998-08-09', '110101199003076739', '汉', '北京市海淀区', '本科', '清华大学', '13928765432', 'zxj@qq.com', '北京市海淀区', '2021-09-01', '中共党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('167', 'gy', '123456', '23', '0', '高耀', '男', '1998-08-09', '130434198404135245', '汉', '山西省太原市', '本科', '西安科技大学', '14728765432', 'gy@qq.com', '山西省太原市', '2021-09-01', '中共预备党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('168', 'glc', '123456', '23', '0', '郭林川', '男', '1998-08-09', '110110199001010011', '汉', '山西省太原市', '本科', '运城学院', '18922765432', 'glc@qq.com', '山西省太原市', '2021-09-01', '中共党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('169', 'pcj', '123456', '23', '0', '潘长姜', '男', '1998-08-09', '130701199310302288', '汉', '山西省太原市', '本科', '太原师范学院', '18924765432', 'pcj@qq.com', '山西省太原市', '2021-09-01', '共青团员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('170', 'lyf', '123456', '23', '0', '李易峰', '男', '1998-08-09', '331023197411211712', '汉', '山西省太原市', '本科', '中北大学', '18828765432', 'lyf@qq.com', '山西省太原市', '2021-09-01', '中共党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('171', 'lxy', '123456', '23', '0', '梁小与', '女', '1998-08-09', '37098319910915624X', '汉', '北京市海淀区', '本科', '北京航空航天大学', '18628765432', 'lxy@qq.com', '北京市海淀区', '2021-09-01', '中共预备党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('172', 'ml', '123456', '23', '0', '马露', '女', '1998-08-09', '141082199203260038', '汉', '山西省太原市', '本科', '西安科技大学', '18528765432', 'ml@qq.com', '山西省太原市', '2021-09-01', '共青团员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('173', 'zc', '123456', '23', '0', '张晨', '男', '1998-08-09', '110101199003078056', '汉', '山西省太原市', '本科', '浙江大学', '18328765432', 'zc@qq.com', '山西省太原市', '2021-09-01', '中共党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('174', 'xtf', '123456', '23', '0', '谢霆锋', '男', '1998-08-09', '11010119900307889X', '汉', '山西省太原市', '本科', '华南理工大学', '18228765432', 'xtf@qq.com', '山西省太原市', '2021-09-01', '中共党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('175', 'dm', '123456', '23', '0', '丁淼', '女', '1998-08-09', '140203198510192880', '汉', '山西省太原市', '本科', '山西财经大学', '18128765432', 'dm@qq.com', '山西省太原市', '2021-09-01', '共青团员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('176', 'lby', '123456', '23', '0', '刘奔于', '男', '1998-08-09', '110101199003078670', '汉', '山西省太原市', '本科', '山东大学', '18028765432', 'lby@qq.com', '山西省太原市', '2021-09-01', '中共党员', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('177', 'wh', '123456', '23', '0', '王华', '女', '1998-08-09', '150623197403061724', '汉', '山西省太原市', '本科', '山东财经大学', '13428765432', 'wh@qq.com', '山西省太原市', '2021-09-01', '群众', 'http://photocq.photo.store.qq.com/psc?/V10Coxr741CNCo/PF0gTXIfeYSjJmkdy2Sn0L8oDlvhXhYIhjwwjqzhPZHG3QY60X*zWof10UOxEEp4Pc0200PmKnRvzwKq3EcBLA!!/mnull&bo=4AHgAQAAAAABByA!&rf=photolist&t=5');
INSERT INTO `user` VALUES ('184', 'lb', '123456', '21', '0', '李波', '男', '1999-09-08', '61012519920701351X', '汉', '北京市', '专科', '吕梁学院', '13383587556', '28601162651@qq.com', '山西省文水县', '2021-09-01', '中共党员', 'http://bpic.588ku.com/element_origin_min_pic/00/85/67/3156e965da25551.jpg');
