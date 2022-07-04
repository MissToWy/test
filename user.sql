/*
Navicat MySQL Data Transfer

Source Server         : User
Source Server Version : 50087
Source Host           : localhost:3306
Source Database       : user

Target Server Type    : MYSQL
Target Server Version : 50087
File Encoding         : 65001

Date: 2022-06-20 13:00:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book`
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bh` int(11) NOT NULL auto_increment,
  `bookname` varchar(225) default NULL,
  `money` int(225) default NULL,
  `guishu` int(11) default NULL,
  `zz` varchar(225) default NULL,
  `time` date default NULL,
  `fx` varchar(225) default NULL,
  PRIMARY KEY  (`bh`),
  KEY `guishu` (`guishu`),
  CONSTRAINT `guishu` FOREIGN KEY (`guishu`) REFERENCES `user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '《明朝那些事》', '23', '1', '掌声', '2022-05-31', '是');
INSERT INTO `book` VALUES ('2', '《明朝那些事》', '23', '2', '掌声', '2022-02-09', '否');
INSERT INTO `book` VALUES ('3', '《不科学的最科学》', '43', '1', '哈', '2022-05-31', '否');
INSERT INTO `book` VALUES ('8', '《图书》', '55', '1', 'mesitu', '2022-05-30', '否');
INSERT INTO `book` VALUES ('10', '《是否》', '32', '1', '应', '2022-05-31', '是');
INSERT INTO `book` VALUES ('11', '《尴尬》', '32', '1', '时多', '2022-05-31', '是');
INSERT INTO `book` VALUES ('12', '《法国》', '12', '1', '第三方', '2022-05-31', '是');
INSERT INTO `book` VALUES ('13', '《水电费》', '32', '1', '士大', '2022-06-20', '是');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `nike_name` varchar(255) default NULL COMMENT '昵称',
  `age` int(11) default NULL,
  `sex` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `gl` int(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', 'admin', '12', '男', '中国', '1');
INSERT INTO `user` VALUES ('2', '1223', '122', '222', '222', '女', '23', '0');
INSERT INTO `user` VALUES ('4', 'sd', 'sdds', 'sdad', '12', '女', 'sff', '0');
INSERT INTO `user` VALUES ('5', '123', '12', '223', '123', '男', '123', '0');
INSERT INTO `user` VALUES ('6', 'asd', '122', 'www', '12', '男', '123', '0');
