CREATE TABLE `comment`  (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(255) NOT NULL COMMENT '这是评论所属的问题id',
  `type` int(11) NOT NULL COMMENT '是1级评论还是2级评论',
  `content` varchar(1024) NOT NULL,
  `commentator` int(11) NOT NULL COMMENT '评论人',
  `gmt_create` bigint(255) NOT NULL,
  `gmt_modified` bigint(255) NOT NULL,
  `like_count` bigint(255) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8;