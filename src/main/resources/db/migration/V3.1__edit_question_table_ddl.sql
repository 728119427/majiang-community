DROP TABLE question  ;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `comment_count` int(11)  DEFAULT 0,
  `creator` int(11) DEFAULT NULL,
  `gmt_modified` bigint(255) DEFAULT NULL,
  `gmt_create` bigint(255) DEFAULT NULL,
  `like_count` int(11)  DEFAULT 0,
  `tag` varchar(255) DEFAULT NULL,
  `description` text,
  `view_count` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;