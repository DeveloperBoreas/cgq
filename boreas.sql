/*
Navicat MySQL Data Transfer

Source Server         : boreas
Source Server Version : 50721
Source Host           : 127.0.0.1:3306
Source Database       : boreas

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-03-10 02:02:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for clipue
-- ----------------------------
DROP TABLE IF EXISTS `clipue`;
CREATE TABLE `clipue` (
  `id` int(11) NOT NULL,
  `clipue_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clipue
-- ----------------------------
INSERT INTO `clipue` VALUES ('1', '计算机系');
INSERT INTO `clipue` VALUES ('11', '中文系');

-- ----------------------------
-- Table structure for com_position
-- ----------------------------
DROP TABLE IF EXISTS `com_position`;
CREATE TABLE `com_position` (
  `id` int(11) NOT NULL,
  `book_auther` varchar(255) DEFAULT NULL,
  `book_bear_palm` varchar(255) DEFAULT NULL,
  `book_char_num` varchar(255) DEFAULT NULL,
  `book_money` varchar(255) DEFAULT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `book_other_auther` varchar(255) DEFAULT NULL,
  `book_periodical_code` varchar(255) DEFAULT NULL,
  `book_press` varchar(255) DEFAULT NULL,
  `book_press_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of com_position
-- ----------------------------
INSERT INTO `com_position` VALUES ('4', 'c程序设计', '非常难受', '999', '2020-01-30', 'c程序设计', 'c程序设计', '1234325', '新华出版社', '1970-01-01');
INSERT INTO `com_position` VALUES ('23', '罗贯中', '难受', '88', '2020-01-30', '水浒传', '罗贯中', '12345', '新华出版社', '1970-01-01');
INSERT INTO `com_position` VALUES ('24', '曹雪芹', '很难受', '66', '2020-01-30', '红楼梦', '罗贯中', '54321', '新华出版社', '1970-01-01');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('25');
INSERT INTO `hibernate_sequence` VALUES ('25');
INSERT INTO `hibernate_sequence` VALUES ('25');
INSERT INTO `hibernate_sequence` VALUES ('25');
INSERT INTO `hibernate_sequence` VALUES ('25');

-- ----------------------------
-- Table structure for research_paper_info
-- ----------------------------
DROP TABLE IF EXISTS `research_paper_info`;
CREATE TABLE `research_paper_info` (
  `id` int(11) NOT NULL,
  `paper_author` varchar(255) DEFAULT NULL,
  `paper_bear_palm` varchar(255) DEFAULT NULL,
  `paper_category` varchar(255) DEFAULT NULL,
  `paper_name` varchar(255) DEFAULT NULL,
  `paper_otherauthor` varchar(255) DEFAULT NULL,
  `paper_periodical_code` varchar(255) DEFAULT NULL,
  `paper_publish_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of research_paper_info
-- ----------------------------
INSERT INTO `research_paper_info` VALUES ('2', '常国庆', '非常难受', '古书', '科研管理APP', '常国庆', '9876', '2020-01-30');
INSERT INTO `research_paper_info` VALUES ('21', '常国庆', '难受', '古书', '论三国', '常国庆', '123123', '2020-01-30');
INSERT INTO `research_paper_info` VALUES ('22', '常国庆', '很难受', '古书', '论水浒', '常国庆', '23456', '2020-01-30');

-- ----------------------------
-- Table structure for research_pro_info
-- ----------------------------
DROP TABLE IF EXISTS `research_pro_info`;
CREATE TABLE `research_pro_info` (
  `id` int(11) NOT NULL,
  `pro_bear_palm` varchar(255) DEFAULT NULL,
  `pro_current_status` varchar(255) DEFAULT NULL,
  `pro_finish_date` date DEFAULT NULL,
  `pro_level` int(11) NOT NULL,
  `pro_money` varchar(255) DEFAULT NULL,
  `pro_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of research_pro_info
-- ----------------------------
INSERT INTO `research_pro_info` VALUES ('5', '', '50%', '2020-01-30', '1', '5万', '基于安卓科研管理APP的设计');
INSERT INTO `research_pro_info` VALUES ('12', '卧槽', '20%', '2020-01-30', '1', '124万', '什么鬼');
INSERT INTO `research_pro_info` VALUES ('13', '无情', '30%', '2020-01-30', '2', '432432万', '神经病啊');
INSERT INTO `research_pro_info` VALUES ('14', '啊', '40%', '2020-01-30', '3', '54353万', '增高鞋垫');
INSERT INTO `research_pro_info` VALUES ('18', '你觉得有吗', '20%', '2020-01-30', '1', '124.00 ', '太阳能发电项目');
INSERT INTO `research_pro_info` VALUES ('19', '你觉得有吗', '30%', '2020-01-30', '2', '432432.00 ', '西气东输项目');
INSERT INTO `research_pro_info` VALUES ('20', '你觉得有吗', '40%', '2020-01-30', '3', '54353.00 ', '南水北调项目');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_alias` varchar(255) NOT NULL,
  `user_birthday` date DEFAULT NULL,
  `user_clipue` varchar(255) NOT NULL,
  `user_composition_ids` varchar(255) DEFAULT NULL,
  `user_jobtitle` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_permission` int(11) NOT NULL,
  `user_research_paper_ids` varchar(255) DEFAULT NULL,
  `user_research_pro_ids` varchar(255) DEFAULT NULL,
  `user_sex` varchar(255) NOT NULL,
  `user_telephone` varchar(255) DEFAULT NULL,
  `user_head_icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', null, 'admin', '2020-01-27', '', '', '', 'admin', 'chang', '1', '', '', '男', '18735783704', null);
INSERT INTO `user_info` VALUES ('8', null, '小丽', '1902-01-01', '1', '4', '讲师', 'xl', '951', '0', '2', '5', '女', '18735783706', 'images/20200219143348_img-79639d1314b4e0418388abc9c7f2ffee.jpg');
INSERT INTO `user_info` VALUES ('7', null, '小明', '1900-01-01', '1', '4', '讲师', 'xm', '987', '0', '2', '5', '男', '18735783702', 'images/20200225211206_mmexport1582469955789.jpg');
INSERT INTO `user_info` VALUES ('10', null, 'Mars', '1985-01-01', '11', '4', '博士', 'Mars', '951000', '0', '2', '5', '男', '18735783709', null);
