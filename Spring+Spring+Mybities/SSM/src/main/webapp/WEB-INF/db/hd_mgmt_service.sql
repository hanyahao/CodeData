/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : hd_mgmt_service

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-02-28 15:21:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for c3p0testtable
-- ----------------------------
DROP TABLE IF EXISTS `c3p0testtable`;
CREATE TABLE `c3p0testtable` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c3p0testtable
-- ----------------------------

-- ----------------------------
-- Table structure for commu_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `commu_exception_log`;
CREATE TABLE `commu_exception_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(45) NOT NULL,
  `method` varchar(45) NOT NULL,
  `parameter` longtext,
  `content` longtext,
  `create_datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of commu_exception_log
-- ----------------------------
INSERT INTO `commu_exception_log` VALUES ('1', '2', '2', '2', '2', '2017-02-28 15:20:13');

-- ----------------------------
-- Table structure for commu_terminal_status
-- ----------------------------
DROP TABLE IF EXISTS `commu_terminal_status`;
CREATE TABLE `commu_terminal_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `terminal_number` varchar(45) NOT NULL,
  `cpu_load` varchar(45) DEFAULT NULL,
  `memory_load` varchar(45) DEFAULT NULL,
  `device_info` longtext,
  `report_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commu_terminal_status
-- ----------------------------

-- ----------------------------
-- Table structure for commu_ws_command_log
-- ----------------------------
DROP TABLE IF EXISTS `commu_ws_command_log`;
CREATE TABLE `commu_ws_command_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `terminal_number` varchar(45) NOT NULL,
  `direction` int(11) DEFAULT NULL,
  `cmd_code` varchar(45) NOT NULL,
  `cmd_serial_no` varchar(45) NOT NULL,
  `cmd_raw_txt` varchar(45) NOT NULL,
  `resp_code` varchar(45) DEFAULT NULL,
  `resp_msg` varchar(45) DEFAULT NULL,
  `resp_raw_txt` longtext,
  `status` int(11) DEFAULT NULL,
  `create_datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commu_ws_command_log
-- ----------------------------
