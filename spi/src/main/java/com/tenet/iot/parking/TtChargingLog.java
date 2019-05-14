package com.tenet.iot.parking;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 充电记录类
 */
@TableName("tt_charging_log")
public class TtChargingLog implements Serializable {
    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("CODE")
    private String code;//充电桩编码
    @TableField("PLATE_NUM")
    private String plateNum;//车牌号码
    @TableField("CHARGE_START_TIME")
    private Date chargeStartTime;//充电开始时间
    @TableField("CHARGE_END_TIME")
    private Date chargeEndTime;//充电结束时间
    @TableField("CHARGE_TIME")
    private Integer chargeTime;//充电时间
    @TableField("CHARGE_MONEY")
    private BigDecimal chargeMoney;//充电金额
    @TableField("CHARGE_ELE")
    private BigDecimal chargeEle;//本次充电
    @TableField("ELE_METER_NUM_BEFORE")
    private BigDecimal eleMeterNumBefore;//充点前电表读数
    @TableField("ELE_METER_NUM_AFTER")
    private BigDecimal eleMeterNumAfter;//充点后电表读数
    @TableField("ELE_TYPE")
    private Integer eleType;//充电类型，1 直流 2 交流
    @TableField("CHARGE_CARD_NUM")
    private String chargeCardNum;//充电卡号
    @TableField("CHARGE_TYPE")
    private Integer chargeType;//充电类型 0 :充满为止 2:金额控制充电 3:电量控制充电
    @TableField("CHARGE_VALUE")
    private BigDecimal chargeValue;
    @TableField("CHARGE_SER_NUM")
    private String chargeSerNum;//充电流水号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public Date getChargeStartTime() {
        return chargeStartTime == null ? null : (Date) chargeStartTime.clone();
    }

    public void setChargeStartTime(Date chargeStartTime) {
        this.chargeStartTime = chargeStartTime == null ? null : (Date) chargeStartTime.clone();
    }

    public Date getChargeEndTime() {
        return chargeEndTime == null ? null : (Date) chargeEndTime.clone();
    }

    public void setChargeEndTime(Date chargeEndTime) {
        this.chargeEndTime = chargeEndTime == null ? null : (Date) chargeEndTime.clone();
    }

    public Integer getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Integer chargeTime) {
        this.chargeTime = chargeTime;
    }

    public BigDecimal getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(BigDecimal chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public BigDecimal getChargeEle() {
        return chargeEle;
    }

    public void setChargeEle(BigDecimal chargeEle) {
        this.chargeEle = chargeEle;
    }

    public BigDecimal getEleMeterNumBefore() {
        return eleMeterNumBefore;
    }

    public void setEleMeterNumBefore(BigDecimal eleMeterNumBefore) {
        this.eleMeterNumBefore = eleMeterNumBefore;
    }

    public BigDecimal getEleMeterNumAfter() {
        return eleMeterNumAfter;
    }

    public void setEleMeterNumAfter(BigDecimal eleMeterNumAfter) {
        this.eleMeterNumAfter = eleMeterNumAfter;
    }

    public Integer getEleType() {
        return eleType;
    }

    public void setEleType(Integer eleType) {
        this.eleType = eleType;
    }

    public String getChargeCardNum() {
        return chargeCardNum;
    }

    public void setChargeCardNum(String chargeCardNum) {
        this.chargeCardNum = chargeCardNum;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public BigDecimal getChargeValue() {
        return chargeValue;
    }

    public void setChargeValue(BigDecimal chargeValue) {
        this.chargeValue = chargeValue;
    }

    public String getChargeSerNum() {
        return chargeSerNum;
    }

    public void setChargeSerNum(String chargeSerNum) {
        this.chargeSerNum = chargeSerNum;
    }
}
