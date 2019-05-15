package com.tenet.iot.parking.charging;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tenet.iot.lock.SyncLockService;
import com.tenet.iot.lock.TtSyncLock;
import com.tenet.iot.mapper.SyncLockMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.function.Consumer;

@Service
public class SyncLockServiceImpl extends ServiceImpl<SyncLockMapper, TtSyncLock> implements SyncLockService {
    @Override
    @Transactional
    public boolean lock(String code) {
        TtSyncLock ttSyncLock = new TtSyncLock();
        ttSyncLock.setLockTm(new Date());
        ttSyncLock.setLastAliveTm(new Date());
        return updateWrapper(ttSyncLock, wrapper -> wrapper.eq("LOCK_STATE", 0).eq("LOCK_CODE", code));
    }

    @Override
    public void unlock(String code) {
        TtSyncLock ttSyncLock = new TtSyncLock();
        ttSyncLock.setLastAliveTm(new Date());
        ttSyncLock.setLockState(0);
        updateWrapper(ttSyncLock, wrapper -> wrapper.eq("LOCK_CODE", code));
    }

    private boolean updateWrapper(TtSyncLock ttSyncLock, Consumer<UpdateWrapper<TtSyncLock>> consumer) {
        UpdateWrapper<TtSyncLock> wrapper = new UpdateWrapper<>();
        if (consumer != null) {
            consumer.accept(wrapper);
        }
        return update(ttSyncLock, wrapper);
    }
}
