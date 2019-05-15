-- ---------------------------------------------------------------------------
-- --------- 【注意】1.脚本文件编码：UTF-8(无BOM)； 
-- ----------------- 2.普通数据库脚本最后要以”;”结尾
-- ----------------- 3.脚本注释格式：-- 注释说明   姓名  年-月-日 时:分
-- ---------------------------------------------------------------------------

-- tt_charging_log 充电  邢培月  2019-05-14  19:53
CREATE TABLE `tt_charging` (
  `ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `CODE` VARCHAR(30) NOT NULL COMMENT '充电桩编码',
  `START_TIME` DATETIME  NOT NULL COMMENT '充电开始时间',
  `SITE_ID` VARCHAR(20) NOT NULL COMMENT '充电桩ID',
  `PILE_ID` VARCHAR(20) NOT NULL COMMENT '充电桩编号',
  `MEMBER_ID` VARCHAR(20) NOT NULL COMMENT '会员编号',
  `END_TIME` DATETIME NOT NULL COMMENT '充电结束时间',
  `STATUS` CHAR(1)  DEFAULT '1' COMMENT '充电状态，1 running 2 full',
  `CHARGE_TIME` INT(4) NOT NULL COMMENT '充电时间(分钟)',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='充电记录';
-- tt_member 会员表 邢培月  2019-05-14  19:53
CREATE TABLE `tt_member` (
  `ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `MEMBER_ID` VARCHAR(20) NOT NULL COMMENT '会员ID',
  `PLATE_NUM` VARCHAR(10)  NOT NULL COMMENT '车牌号码',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员表';
-- tt_charging_log 充电记录临时表 邢培月  2019-05-15  10:10
CREATE TABLE `tt_charging_log` (
  `ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `CHARGE_ID` BIGINT(20) NOT NULL COMMENT '充电单号',
  `STATUS` CHAR(1)  DEFAULT '1' COMMENT '充电状态，1 running 2 full',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='充电记录临时表';
CREATE UNIQUE INDEX unique_index_chargeId ON tt_charging_log(CHARGE_ID);

-- ts_sync_lock 多节点部署服务同步锁表 邢培月 2019-05-15 20:00
CREATE TABLE `tt_sync_lock` (
  `LOCK_CODE` varchar(30) NOT NULL COMMENT '锁名称，英文、数字、下划线组成',
  `LAST_ALIVE_TM` datetime DEFAULT NULL COMMENT '最后活跃时间',
  `LOCK_TM` datetime DEFAULT NULL COMMENT '获取锁时间',
  `LOCK_STATE` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '锁定状态：1锁定 0未锁定',
  PRIMARY KEY (`LOCK_CODE`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='多节点同步锁表';
INSERT INTO ts_sync_lock(LOCK_CODE,LOCK_STATE) VALUES("charge_order_job",0);
INSERT INTO ts_sync_lock(LOCK_CODE,LOCK_STATE) VALUES("charge_status_job",0);





