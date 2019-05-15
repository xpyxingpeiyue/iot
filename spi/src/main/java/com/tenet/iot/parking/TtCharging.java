package com.tenet.iot.parking;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 充电记录类
 */
@TableName("tt_charging")
public class TtCharging implements Serializable {
    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("CODE")
    private String code;//充电桩编码
    @TableField("START_TIME")
    private Date startTime;//充电开始时间
    @TableField("SITE_ID")
    private String siteId;//充电桩ID
    @TableField("PILE_ID")
    private String pileId;//充电桩编号
    @TableField("MEMBER_ID")
    private String memberId;//会员编号
    @TableField("END_TIME")
    private Date endTime;//充电结束时间
    @TableField("STATUS")
    private Integer status;//充电状态，1 running 2 full
    @TableField("CHARGE_TIME")
    private Integer chargeTime;//充电时间

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

    public Date getStartTime() {
        return startTime == null ? null : (Date) startTime.clone();
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime == null ? null : (Date) startTime.clone();
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getPileId() {
        return pileId;
    }

    public void setPileId(String pileId) {
        this.pileId = pileId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getEndTime() {
        return endTime == null ? null : (Date) endTime.clone();
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime == null ? null : (Date) endTime.clone();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getChargeTime() {
        return chargeTime;
    }

    public void setChargeTime(Integer chargeTime) {
        this.chargeTime = chargeTime;
    }
}
