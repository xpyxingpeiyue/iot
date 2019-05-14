-- ---------------------------------------------------------------------------
-- --------- 【注意】1.脚本文件编码：UTF-8(无BOM)； 
-- ----------------- 2.普通数据库脚本最后要以”;”结尾
-- ----------------- 3.脚本注释格式：-- 注释说明   姓名  年-月-日 时:分
-- ---------------------------------------------------------------------------

-- tt_charging_log 充电  邢培月  2019-05-14  19:53
CREATE TABLE `tt_charging_log` (
  `ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `CODE` VARCHAR(30) NOT NULL COMMENT '充电桩编码',
  `PLATE_NUM` VARCHAR(20) NOT NULL COMMENT '车牌号码',
  `CHARGE_START_TIME` DATETIME  NOT NULL COMMENT '充电开始时间',
  `CHARGE_END_TIME` DATETIME NOT NULL COMMENT '充电结束时间',
  `CHARGE_TIME` INT(4) NOT NULL COMMENT '充电时间(分钟)',
  `CHARGE_MONEY` DECIMAL(20,2) NOT NULL COMMENT '充电金额',
  `CHARGE_ELE` DECIMAL(20,2) NOT NULL COMMENT '本次充电',
  `ELE_METER_NUM_BEFORE` DECIMAL(20,2) NOT NULL COMMENT '充点前电表读数',
  `ELE_METER_NUM_AFTER` DECIMAL(20,2) NOT NULL COMMENT '充点后电表读数',
  `ELE_TYPE` CHAR(1)  DEFAULT '2' COMMENT '充电类型，1 直流 2 交流',
  `CHARGE_CARD_NUM` VARCHAR(30) NULL COMMENT '充电卡号',
  `CHARGE_TYPE` CHAR(1) DEFAULT '0' NULL COMMENT '充电类型 0 :充满为止 2:金额控制充电 3:电量控制充电',
  `CHARGE_VALUE` DECIMAL(20,2) DEFAULT 0 COMMENT '充电值单位1分钟，金额单位为1元，电量时单位为1kwh',
  `CHARGE_SER_NUM` VARCHAR(60)  NULL COMMENT '充电流水号',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='充电记录';




