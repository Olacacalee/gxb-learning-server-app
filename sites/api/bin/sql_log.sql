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

DROP TABLE  IF EXISTS `car_fix`.`fix_order`;
CREATE TABLE `car_fix`.`fix_order` (
	`fix_order_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '工单id',
	`car_type` varchar(50) NOT NULL COMMENT '车型',
	`car_number` varchar(50) NOT NULL COMMENT '车牌号',
	`driver` varchar(50) NOT NULL COMMENT '送修人',
	`driver_mobile` varchar(50) NOT NULL COMMENT '送修人手机号',
	`send_time` datetime NOT NULL COMMENT '送修时间',
	`fix_status` varchar(4) NOT NULL COMMENT '维修状态  0未维修  1.维修中  2.已维修',
	`finish_time` datetime COMMENT '完成时间',
	`price` decimal(10,2) COMMENT '维修价格',
	`fixer` varchar(50) COMMENT '维修工',
	`created_at` datetime COMMENT '工单创建时间',
	`updated_at` datetime COMMENT '工单更新时间',
	PRIMARY KEY (`fix_order_id`)
) COMMENT='';

CREATE TABLE `car_fix`.`contact` (
	`contact_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键，联系人id',
	`contact_name` varchar(100) NOT NULL COMMENT '联系人姓名',
	`contact_mobile` varchar(50) COMMENT '联系人手机号',
	`contact_car_type` varchar(100) COMMENT '联系人的车型',
	`contact_car_number` varchar(100) COMMENT '联系人车牌号',
	`created_at` datetime NOT NULL COMMENT '创建时间',
	`updated_at` datetime COMMENT '更新时间',
	`delete_flag` tinyint(4),
	PRIMARY KEY (`contact_id`)
) COMMENT='';

CREATE TABLE `car_fix`.`tenant` (
	`tenant_id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '租户id',
	`tenant_name` varchar(100) NOT NULL COMMENT '租户名称',
	`shortname` varchar(100) NOT NULL COMMENT '二级域名',
	`created_at` datetime NOT NULL COMMENT '创建时间',
	`updated_at` datetime NOT NULL,
	`delete_flag` tinyint(4) NOT NULL,
	PRIMARY KEY (`tenant_id`)
) COMMENT='';