CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `comment_count` int(11) unsigned zerofill DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `gmt_modified` bigint(255) DEFAULT NULL,
  `gmt_create` bigint(255) DEFAULT NULL,
  `like_count` int(11) unsigned zerofill DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `description` text,
  `view_count` int(11) unsigned zerofill NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;