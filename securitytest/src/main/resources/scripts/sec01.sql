# Host: 127.0.0.1  (Version 5.6.39-log)
# Date: 2019-08-24 13:18:23
# Generator: MySQL-Front 5.4  (Build 4.153) - http://www.mysqlfront.de/

/*!40101 SET NAMES utf8 */;

#
# Structure for table "my_role"
#

DROP TABLE IF EXISTS `my_role`;
CREATE TABLE `my_role` (
  `Id` int(11) NOT NULL DEFAULT '0',
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "my_role"
#

INSERT INTO `my_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_DBA'),(3,'ROLE_USER');

#
# Structure for table "my_user"
#

DROP TABLE IF EXISTS `my_user`;
CREATE TABLE `my_user` (
  `Id` int(11) NOT NULL DEFAULT '0',
  `login_name` varchar(255) NOT NULL DEFAULT '',
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "my_user"
#

INSERT INTO `my_user` VALUES (1,'admin','$2a$10$bgJic30Uwul4BYfF1kbtju5TFtW4okRKRPUpf6u6LJlBTTzAlMEgO','管理员'),(2,'user1','$2a$10$5gfj2oA4s1q4x4fGYTucdOc0tXSVEIYqJqYhCOA49a5JK4AiKxVau','普通用户');

#
# Structure for table "my_user_role"
#

DROP TABLE IF EXISTS `my_user_role`;
CREATE TABLE `my_user_role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL DEFAULT '',
  `role_id` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

#
# Data for table "my_user_role"
#

INSERT INTO `my_user_role` VALUES (1,'1','1'),(2,'1','2'),(3,'2','3');
