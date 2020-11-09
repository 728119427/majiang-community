## Seven涩问社区
**声明：项目是学习B站视频所做，并非个人原创！在原项目基础上略微做了些改动。**<br/>
[视频地址](https://www.bilibili.com/video/BV1r4411r7au)
<br/>
<br/>

## 在线演示地址
[在线演示](http://117.50.63.69:8080)

<br/>

## 功能列表

> - 发帖
> - 回复
> - 图片上传
> - 热门标签
> - 7天最热，30天最热,消灭零回复
> - 消息通知
> - 搜索
> - github授权登录等！
    
<br/>    
    
## 开发环境及工具
> - IntelliJ IDEA
> - JDK8
> - SpringBoot2.x
> - Maven
> - Git

<br/>   


## 技术栈 
**前端**：
> - BootStrap, jquery

**后端**
> - SpringBoot,UFile(上传图片)
> - MyBatis, Mybatis-generator, pageHelper
> - thymeleaf

**数据库**
> - Mysql
> - Flyway 

<br/>   

## 集成插件或工具   
|   插件名称      |           链接地址                 |
| :------:| :--------------------------:|
| Markdown编辑器| https://pandao.github.io/editor.md/
|UFile SDK|https://github.com/ucloud/ufile-sdk-java|
|Github OAuth|https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/|
|Lombok| https://www.projectlombok.org|

<br/>   

## 数据库表展示
**`项目使用了数据库管理工具flyway，运行项目后会自动生成表，但是需要先创建community数据库`**

数据库表关系
>  一对多: user-question, user-notification, question-comment, comment-comment(回复和子回复) <br/>


**user表**
````sql
CREATE TABLE `user` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `bio`varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `token` varchar(36) DEFAULT NULL,
  `gmt_create` bigint(255) DEFAULT NULL,
  `gmt_modified` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
````
**question表**
````sql
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
````
**comment表**
````sql
CREATE TABLE `comment`  (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(255) NOT NULL COMMENT '这是评论所属的问题id',
  `type` int(11) NOT NULL COMMENT '是1级评论还是2级评论',
  `content` varchar(1024) NOT NULL,
  `comment_count` int(11) DEFAULT 0,
  `commentator` int(11) NOT NULL COMMENT '评论人',
  `gmt_create` bigint(255) NOT NULL,
  `gmt_modified` bigint(255) NOT NULL,
  `like_count` bigint(255) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8;
````
**notification表**
````sql
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
````
**ad(广告)表**
````sql
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
````

<br/>   

## 页面效果展示

![](http://117.50.63.69/upload/2020/11/Snipaste_2020-11-03_18-49-35-14748028ce3142a9b7a7aad2b11c0437.png)

![](http://seven.cn-bj.ufileos.com/%E8%AE%BA%E5%9D%9B%E9%A1%B5%E9%9D%A2%E5%B1%95%E7%A4%BA1.png?UCloudPublicKey=CEnvo7uzX5eCtplVo47O2X4VievOYUd30fyy5QXO3&Signature=SducLVbDUQu%2BTzTa%2FhZcKoMRXXI%3D&Expires=1604930988)

![](http://seven.cn-bj.ufileos.com/%E8%AE%BA%E5%9D%9B%E9%A1%B5%E9%9D%A2%E5%B1%95%E7%A4%BA2.png?UCloudPublicKey=CEnvo7uzX5eCtplVo47O2X4VievOYUd30fyy5QXO3&Signature=XCfSHXGqwRyKuhDkKf4clfm8nto%3D&Expires=1604931048)

<br/>   

## 结语
后续可能还会对该社区进行其他功能的开发,敬请期待！

