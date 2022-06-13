drop table if exists car;
CREATE TABLE `car` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) unsigned not null DEFAULT 0,
  `status` tinyint(1) unsigned not null DEFAULT 1,
  `gmt_create` bigint(20) unsigned not null DEFAULT 0,
  `gmt_modify` bigint(20) unsigned not null DEFAULT 0,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;

drop table if exists car_order;
CREATE TABLE `car_order` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) not null,
  `car_id` bigint(64) unsigned not null,
  `order_status` tinyint(1) unsigned not null DEFAULT 1,
  `gmt_start` bigint(20) unsigned not null DEFAULT 0,
  `gmt_end` bigint(20) unsigned not null DEFAULT 0,
  `status` tinyint(1) unsigned not null DEFAULT 1,
  `gmt_create` bigint(20) unsigned not null DEFAULT 0,
  `gmt_modify` bigint(20) unsigned not null DEFAULT 0,
	PRIMARY KEY (`id`),
	KEY `car_id_idx` (`car_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8;