CREATE TABLE `ad`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT null,
  `url` varchar(512) DEFAULT null,
  `image` varchar(255)  DEFAULT null,
  `gmt_start` bigint(255),
  `gmt_end` bigint(255),
  `gmt_create` bigint(255),
  `gmt_modified` bigint(255),
  `status` int(11),
  `pos` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8;