/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : jfinal_demo

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-05-15 20:20:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for reportcard
-- ----------------------------
DROP TABLE IF EXISTS `reportcard`;
CREATE TABLE `reportcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `studentId` varchar(100) DEFAULT NULL,
  `ldty_grade` float(10,2) DEFAULT '0.00' COMMENT '立定跳远成绩',
  `ldty_score` float(10,2) DEFAULT NULL,
  `qcjsp_grade` float(10,2) DEFAULT '0.00' COMMENT '3/4全场加速跑成绩',
  `qcjsp_score` float(10,2) DEFAULT NULL,
  `zfp_grade` float(10,2) DEFAULT '0.00' COMMENT '15m*17折返跑',
  `zfp_score` float(10,2) DEFAULT NULL,
  `ywqz_grade` float(10,2) DEFAULT '0.00' COMMENT '1min仰卧起坐（次）',
  `ywqz_score` float(10,2) DEFAULT NULL,
  `zpdjtmg_grade` float(10,2) DEFAULT '0.00' COMMENT '助跑单脚跳摸高（m）',
  `zpdjtmg_score` float(10,2) DEFAULT NULL,
  `syts_grade` float(10,2) DEFAULT '0.00' COMMENT '双摇跳绳（次）',
  `syts_score` float(10,2) DEFAULT NULL,
  `tqq_grade` float(10,2) DEFAULT '0.00' COMMENT '体前屈（cm）',
  `tqq_score` float(10,2) DEFAULT NULL,
  `tqsstscq_grade` float(10,2) DEFAULT '0.00' COMMENT '跳起双手头上传球（m）',
  `tqsstscq_score` float(10,2) DEFAULT NULL,
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
