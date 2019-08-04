DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键无意义',
  `user_name` varchar(10) NOT NULL DEFAULT '' COMMENT '用户名称',
  `user_age` int(11) NOT NULL DEFAULT '0' COMMENT '用户年纪',
  `address` varchar(50) NOT NULL DEFAULT '' COMMENT '地址信息',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `remarks` varchar(50) NOT NULL DEFAULT 'slaver' COMMENT '备注信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES ('1', 'u1', '20', 'slaver', '2019-08-04 17:12:53', 'db2');
INSERT INTO `user` VALUES ('2', 'u2', '22', 'slaver', '2019-08-04 17:12:53', 'db2');
