DROP TABLE  IF EXISTS `car_fix`.`user`;
CREATE TABLE `car_fix`.`user` (
	`user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
	`username` varchar(100) NOT NULL COMMENT '登录账号',
	`password` varchar(50) NOT NULL COMMENT '密码',
	`name` varchar(100) COMMENT '姓名',
	`mobile` VARCHAR(20) COMMENT '手机号',
	`created_at` datetime NOT NULL COMMENT '创建时间',
	`updated_at` datetime NOT NULL COMMENT '更新时间',
	`last_login_time` datetime NOT NULL COMMENT '上次登录时间',
	PRIMARY KEY (`user_id`)
) COMMENT='';