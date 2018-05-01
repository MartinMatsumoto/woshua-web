/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : wo_shua

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-05-01 23:14:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for catalogue
-- ----------------------------
DROP TABLE IF EXISTS `catalogue`;
CREATE TABLE `catalogue` (
  `id` varchar(45) NOT NULL,
  `url` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `position` varchar(45) DEFAULT NULL,
  `major` bigint(11) DEFAULT '0',
  `grade` bigint(11) DEFAULT '0',
  `parent` varchar(45) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`url`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for catalogue_praxis
-- ----------------------------
DROP TABLE IF EXISTS `catalogue_praxis`;
CREATE TABLE `catalogue_praxis` (
  `catalogue_id` varchar(45) DEFAULT NULL,
  `catalogue_url` varchar(50) NOT NULL,
  `praxis_id` varchar(15) NOT NULL,
  PRIMARY KEY (`catalogue_url`,`praxis_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for enable_page_status
-- ----------------------------
DROP TABLE IF EXISTS `enable_page_status`;
CREATE TABLE `enable_page_status` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `grade_id` bigint(11) NOT NULL,
  `major_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for maptree
-- ----------------------------
DROP TABLE IF EXISTS `maptree`;
CREATE TABLE `maptree` (
  `id` bigint(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `intro` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parent_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for maptree_relation
-- ----------------------------
DROP TABLE IF EXISTS `maptree_relation`;
CREATE TABLE `maptree_relation` (
  `this_id` bigint(11) NOT NULL,
  `parent_id` bigint(11) NOT NULL,
  PRIMARY KEY (`this_id`,`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Table structure for praxis
-- ----------------------------
DROP TABLE IF EXISTS `praxis`;
CREATE TABLE `praxis` (
  `id` varchar(15) NOT NULL,
  `click_rate` varchar(15) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  `grade` bigint(11) DEFAULT '0',
  `major` bigint(11) DEFAULT '0',
  `content` longtext,
  `prompt` longtext,
  `resolve` longtext,
  `answer` longtext,
  `good_num` varchar(15) DEFAULT NULL,
  `bad_num` varchar(15) DEFAULT NULL,
  `dificulty` varchar(45) DEFAULT '-1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `account` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `user_type` bigint(11) NOT NULL DEFAULT '1',
  `sex` bigint(11) NOT NULL DEFAULT '1',
  `icon_path` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_favorite
-- ----------------------------
DROP TABLE IF EXISTS `user_favorite`;
CREATE TABLE `user_favorite` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `praxis_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_statistic
-- ----------------------------
DROP TABLE IF EXISTS `user_statistic`;
CREATE TABLE `user_statistic` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `favorite_num` bigint(11) NOT NULL,
  `user_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
