package com.tenet.iot.lock;

import com.baomidou.mybatisplus.extension.service.IService;

public interface SyncLockService extends IService<TtSyncLock> {
    boolean lock(String code);
    void unlock(String code);
}
