/*
 Navicat MySQL Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50648
 Source Host           : localhost
 Source Database       : monkey

 Target Server Type    : MySQL
 Target Server Version : 50648
 File Encoding         : utf-8

 Date: 08/09/2020 09:48:05 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `demo_user`
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `register_date` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_flag` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `demo_user`
-- ----------------------------
BEGIN;
INSERT INTO `demo_user` VALUES ('46', 'Tt0ekpw8c90ld', 'Wvuio0jph', 'xiaoliuzhuoqianmingyuexiaolixiaoliuzhuoqianmingyuexiaoli', '3y2d_zcehb14d@JOBq.ape', '0', '2018-11-23 00:00:00', '2020-08-08 10:18:53', null, null, null, '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
