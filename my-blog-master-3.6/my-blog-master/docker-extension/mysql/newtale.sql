/*
Navicat MySQL Data Transfer

Source Server         : localdb
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : tale

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-03-05 16:40:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_attach
-- ----------------------------
DROP TABLE IF EXISTS `t_attach`;
CREATE TABLE `t_attach` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL DEFAULT '',
  `ftype` varchar(50) DEFAULT '',
  `fkey` varchar(100) NOT NULL DEFAULT '',
  `author_id` int(10) DEFAULT NULL,
  `created` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_attach
-- ----------------------------
INSERT INTO `t_attach` VALUES ('1', '中国科学技术大学软院双旦晚会策划案（节目清单修订版).docx', 'file', '/upload/2018/12/6t52gkeblqg85qts7ucd9h1ilk.docx', '1', '1545665511');
INSERT INTO `t_attach` VALUES ('2', 'token.json', 'file', '/upload/2018/12/o585rqq2r2ghkpsvtj6daig7ak.json', '1', '1545665562');

-- ----------------------------
-- Table structure for t_comments
-- ----------------------------
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE `t_comments` (
  `coid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `cid` int(10) unsigned DEFAULT '0',
  `created` int(10) unsigned DEFAULT '0',
  `author` varchar(200) DEFAULT NULL,
  `author_id` int(10) unsigned DEFAULT '0',
  `owner_id` int(10) unsigned DEFAULT '0',
  `mail` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL,
  `content` text,
  `type` varchar(16) DEFAULT 'comment',
  `status` varchar(16) DEFAULT 'approved',
  `parent` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`coid`),
  KEY `cid` (`cid`),
  KEY `created` (`created`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comments
-- ----------------------------
INSERT INTO `t_comments` VALUES ('1', '2', '1545653305', '热心网友', '0', '1', '', '', '0:0:0:0:0:0:0:1', null, '测试评论测试', 'comment', 'approved', '0');

-- ----------------------------
-- Table structure for t_contents
-- ----------------------------
DROP TABLE IF EXISTS `t_contents`;
CREATE TABLE `t_contents` (
  `cid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `modified` int(10) unsigned DEFAULT '0',
  `content` text COMMENT '内容文字',
  `author_id` int(10) unsigned DEFAULT '0',
  `type` varchar(16) DEFAULT 'post',
  `status` varchar(16) DEFAULT 'publish',
  `tags` varchar(200) DEFAULT NULL,
  `categories` varchar(200) DEFAULT NULL,
  `hits` int(10) unsigned DEFAULT '0',
  `comments_num` int(10) unsigned DEFAULT '0',
  `allow_comment` tinyint(1) DEFAULT '1',
  `allow_ping` tinyint(1) DEFAULT '1',
  `allow_feed` tinyint(1) DEFAULT '1',
  `measure` text COMMENT '内容文字',
  PRIMARY KEY (`cid`),
  UNIQUE KEY `slug` (`slug`),
  KEY `created` (`created`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_contents
-- ----------------------------
INSERT INTO `t_contents` VALUES ('1', 'about my blog', 'about', '1487853610', '1487872488', '### Hello World\r\n\r\nabout me\r\n\r\n### ...\r\n\r\n...', '1', 'page', 'publish', null, null, '0', '0', '1', '1', '1', null);
INSERT INTO `t_contents` VALUES ('2', 'test', null, '1487861184', '1545799262', '12345', '1', 'post', 'publish', '', 'C++', '10', '0', '0', '1', '1', null);
INSERT INTO `t_contents` VALUES ('3', 'HelloWorld', null, '1545799209', '1545799209', '#include <iostream>\r\nusing namespace std;\r\nint main()\r\n{\r\n    using namespace std;\r\n    cout << \"HelloWorld\";\r\n    return 0;\r\n}', '1', 'post', 'publish', '', 'C++', '0', '0', '0', '1', '1', null);
INSERT INTO `t_contents` VALUES ('4', 'test1', null, '1545807940', '1545807940', 'test1', '1', 'post', 'publish', '', 'C++', '0', '0', '1', '1', '1', null);
INSERT INTO `t_contents` VALUES ('5', '测试提交', null, '1550651727', '1550651727', 'import java.util.*', '1', 'post', 'publish', '', 'C++', '0', '0', '1', '1', '1', null);
INSERT INTO `t_contents` VALUES ('6', '56', null, '1551264352', '1551264352', 'HelloWorld', '1', 'post', 'publish', '', 'C++', '0', '0', '1', '1', '1', null);
INSERT INTO `t_contents` VALUES ('7', '123', null, '1551754170', '1551754170', '123123\r\n123123\r\n231231\r\n21321\r\n2', '1', 'post', 'publish', '', 'C++', '0', '0', '1', '1', '1', '总行数:4\n\r空行数：0\n\r代码行数：4\n\r注释行数:0  注释行数比例0.00%\n\r类和结构体个数：0\n\r函数个数：0\n\r总语句数：0\n\r');

-- ----------------------------
-- Table structure for t_logs
-- ----------------------------
DROP TABLE IF EXISTS `t_logs`;
CREATE TABLE `t_logs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `action` varchar(100) DEFAULT NULL,
  `data` varchar(2000) DEFAULT NULL,
  `author_id` int(10) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `created` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_logs
-- ----------------------------
INSERT INTO `t_logs` VALUES ('1', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545050010');
INSERT INTO `t_logs` VALUES ('2', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545292750');
INSERT INTO `t_logs` VALUES ('3', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545369297');
INSERT INTO `t_logs` VALUES ('4', '保存系统设置', '{\"social_zhihu\":\"\",\"social_github\":\"\",\"social_twitter\":\"\",\"social_weibo\":\"测试微博\"}', '1', '0:0:0:0:0:0:0:1', '1545369545');
INSERT INTO `t_logs` VALUES ('5', '保存系统设置', '{\"site_record\":\"\",\"site_description\":\"代码度量系统\",\"site_title\":\"My Blog\",\"site_theme\":\"default\",\"allow_install\":\"\"}', '1', '0:0:0:0:0:0:0:1', '1545370559');
INSERT INTO `t_logs` VALUES ('6', '保存系统设置', '{\"site_record\":\"\",\"site_description\":\"代码度量系统\",\"site_title\":\"代码度量系统\",\"site_theme\":\"default\",\"allow_install\":\"\"}', '1', '0:0:0:0:0:0:0:1', '1545370576');
INSERT INTO `t_logs` VALUES ('7', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545555509');
INSERT INTO `t_logs` VALUES ('8', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545635314');
INSERT INTO `t_logs` VALUES ('9', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545641452');
INSERT INTO `t_logs` VALUES ('10', '修改个人信息', '{\"uid\":1,\"email\":\"785336532@qq.com\",\"screenName\":\"admin\"}', '1', '0:0:0:0:0:0:0:1', '1545642723');
INSERT INTO `t_logs` VALUES ('11', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545649840');
INSERT INTO `t_logs` VALUES ('12', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545652628');
INSERT INTO `t_logs` VALUES ('13', '修改个人信息', '{\"uid\":1,\"email\":\"785336532@qq.com\",\"screenName\":\"测试管理员\"}', '1', '0:0:0:0:0:0:0:1', '1545652990');
INSERT INTO `t_logs` VALUES ('14', '登录后台', null, '1', '127.0.0.1', '1545664523');
INSERT INTO `t_logs` VALUES ('15', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545667525');
INSERT INTO `t_logs` VALUES ('16', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545705960');
INSERT INTO `t_logs` VALUES ('17', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545709153');
INSERT INTO `t_logs` VALUES ('18', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545723361');
INSERT INTO `t_logs` VALUES ('19', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545723547');
INSERT INTO `t_logs` VALUES ('20', '登录后台', null, '1', '0:0:0:0:0:0:0:1', '1545724337');
INSERT INTO `t_logs` VALUES ('21', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545724371');
INSERT INTO `t_logs` VALUES ('22', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545724880');
INSERT INTO `t_logs` VALUES ('23', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545724969');
INSERT INTO `t_logs` VALUES ('24', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545726102');
INSERT INTO `t_logs` VALUES ('25', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545732371');
INSERT INTO `t_logs` VALUES ('26', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545737385');
INSERT INTO `t_logs` VALUES ('27', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545791009');
INSERT INTO `t_logs` VALUES ('28', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545793772');
INSERT INTO `t_logs` VALUES ('29', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545799111');
INSERT INTO `t_logs` VALUES ('30', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545807294');
INSERT INTO `t_logs` VALUES ('31', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545807612');
INSERT INTO `t_logs` VALUES ('32', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545807900');
INSERT INTO `t_logs` VALUES ('33', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545808248');
INSERT INTO `t_logs` VALUES ('34', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1545808337');
INSERT INTO `t_logs` VALUES ('35', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1546525389');
INSERT INTO `t_logs` VALUES ('36', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1546527645');
INSERT INTO `t_logs` VALUES ('37', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1546528123');
INSERT INTO `t_logs` VALUES ('38', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1550646449');
INSERT INTO `t_logs` VALUES ('39', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1550646996');
INSERT INTO `t_logs` VALUES ('40', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1550649832');
INSERT INTO `t_logs` VALUES ('41', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1550650463');
INSERT INTO `t_logs` VALUES ('42', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1551263937');
INSERT INTO `t_logs` VALUES ('43', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1551264245');
INSERT INTO `t_logs` VALUES ('44', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1551750202');
INSERT INTO `t_logs` VALUES ('45', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1551752212');
INSERT INTO `t_logs` VALUES ('46', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1551753177');
INSERT INTO `t_logs` VALUES ('47', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1551753393');
INSERT INTO `t_logs` VALUES ('48', '登录系统', null, '1', '0:0:0:0:0:0:0:1', '1551754157');

-- ----------------------------
-- Table structure for t_metas
-- ----------------------------
DROP TABLE IF EXISTS `t_metas`;
CREATE TABLE `t_metas` (
  `mid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `slug` varchar(200) DEFAULT NULL,
  `type` varchar(32) NOT NULL DEFAULT '',
  `description` varchar(200) DEFAULT NULL,
  `sort` int(10) unsigned DEFAULT '0',
  `parent` int(10) unsigned DEFAULT '0',
  PRIMARY KEY (`mid`),
  KEY `slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_metas
-- ----------------------------
INSERT INTO `t_metas` VALUES ('1', 'C++', null, 'category', null, '0', '0');
INSERT INTO `t_metas` VALUES ('6', 'my github1', 'https://github.com/ZHENFENG13', 'link', '', '0', '0');

-- ----------------------------
-- Table structure for t_options
-- ----------------------------
DROP TABLE IF EXISTS `t_options`;
CREATE TABLE `t_options` (
  `name` varchar(32) NOT NULL DEFAULT '',
  `value` varchar(1000) DEFAULT '',
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_options
-- ----------------------------
INSERT INTO `t_options` VALUES ('allow_install', '', null);
INSERT INTO `t_options` VALUES ('site_description', '代码度量系统', null);
INSERT INTO `t_options` VALUES ('site_keywords', '13 Blog', null);
INSERT INTO `t_options` VALUES ('site_record', '', '备案号');
INSERT INTO `t_options` VALUES ('site_theme', 'default', null);
INSERT INTO `t_options` VALUES ('site_title', '代码度量系统', '');
INSERT INTO `t_options` VALUES ('social_github', '', null);
INSERT INTO `t_options` VALUES ('social_twitter', '', null);
INSERT INTO `t_options` VALUES ('social_weibo', '测试微博', null);
INSERT INTO `t_options` VALUES ('social_zhihu', '', null);

-- ----------------------------
-- Table structure for t_relationships
-- ----------------------------
DROP TABLE IF EXISTS `t_relationships`;
CREATE TABLE `t_relationships` (
  `cid` int(10) unsigned NOT NULL,
  `mid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`cid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_relationships
-- ----------------------------
INSERT INTO `t_relationships` VALUES ('2', '1');
INSERT INTO `t_relationships` VALUES ('3', '1');
INSERT INTO `t_relationships` VALUES ('4', '1');
INSERT INTO `t_relationships` VALUES ('5', '1');
INSERT INTO `t_relationships` VALUES ('6', '1');
INSERT INTO `t_relationships` VALUES ('7', '1');

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `home_url` varchar(200) DEFAULT NULL,
  `screen_name` varchar(32) DEFAULT NULL,
  `created` int(10) unsigned DEFAULT '0',
  `activated` int(10) unsigned DEFAULT '0',
  `logged` int(10) unsigned DEFAULT '0',
  `group_name` varchar(16) DEFAULT 'visitor',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `name` (`username`),
  UNIQUE KEY `mail` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('1', 'admin', 'a66abb5684c45962d887564f08346e8d', '785336532@qq.com', null, '测试管理员', '1490756162', '0', '0', 'visitor');
