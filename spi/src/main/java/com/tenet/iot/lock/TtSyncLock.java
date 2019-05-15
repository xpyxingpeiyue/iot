package com.tenet.iot.lock;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("tt_sync_lock")
public class TtSyncLock implements Serializable {
    @TableField("LOCK_CODE")
    private String lockCode;
    //锁定状态：1锁定 0未锁定
    @TableField("LOCK_STATE")
    private Integer lockState;
    //最后活跃时间
    @TableField("LAST_ALIVE_TM")
    private Date lastAliveTm;
    //获取锁时间
    @TableField("LOCK_TM")
    private Date lockTm;

    public String getLockCode() {
        return lockCode;
    }

    public void setLockCode(String lockCode) {
        this.lockCode = lockCode;
    }

    public Integer getLockState() {
        return lockState;
    }

    public void setLockState(Integer lockState) {
        this.lockState = lockState;
    }

    public Date getLastAliveTm() {
        return lastAliveTm == null ? null : (Date) lastAliveTm.clone();
    }

    public void setLastAliveTm(Date lastAliveTm) {
        this.lastAliveTm = lastAliveTm == null ? null : (Date) lastAliveTm.clone();
    }

    public Date getLockTm() {
        return lockTm == null ? null : (Date) lockTm.clone();
    }

    public void setLockTm(Date lockTm) {
        this.lockTm = lockTm == null ? null : (Date) lockTm.clone();
    }
}
