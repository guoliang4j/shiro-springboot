/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 04/02/2022 14:41:09
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `perms`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `role`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `salt`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account`
VALUES (1, 'zs', '2e30a0c11d72bbf44d6ca49544ae47af', '', '', '440c86dfa5ce49e8b733451c8737a69f');
INSERT INTO `account`
VALUES (2, 'ls', '91a4cdf4f68800fb5bdee3540985d8ed', 'manage', '', 'bfd81043f1454e609f3390064acb944b');
INSERT INTO `account`
VALUES (3, 'ww', '2bccbcd8fc3120f88fe5cc90a1ce2780', 'manage', 'administrator', 'd092340d6ca7404f8585843177eff881');
INSERT INTO `account`
VALUES (7, 'gl', '1e143116a12f142fe61726cecdb6bfab', NULL, NULL, '郭亮');

SET
FOREIGN_KEY_CHECKS = 1;
