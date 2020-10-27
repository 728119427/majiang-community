CREATE TABLE `notification`  (
`id` bigint(255) AUTO_INCREMENT,
`notifier` bigint(255) NOT NULL,
`receiver` bigint(255) NOT NULL,
`outerId` bigint(255) NOT NULL,
`type` int(11) NOT NULL,
`gmt_create` bigint(255) NOT NULL,
`status` int(11) NOT NULL DEFAULT 0,
`notifier_name` varchar(100) NOT NULL,
`outer_title` varchar(256) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8;