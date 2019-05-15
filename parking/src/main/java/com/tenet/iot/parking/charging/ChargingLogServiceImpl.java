package com.tenet.iot.parking.charging;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tenet.iot.mapper.ChargingLogMapper;
import com.tenet.iot.parking.ChargingLogService;
import com.tenet.iot.parking.TtCharging;
import com.tenet.iot.parking.TtChargingLog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;


/**
 * 充电记录实现类
 */
@Service
public class ChargingLogServiceImpl extends ServiceImpl<ChargingLogMapper, TtChargingLog> implements ChargingLogService {

    private List<TtChargingLog> listWrapper(Consumer<LambdaQueryWrapper<TtChargingLog>> consumer) {
        LambdaQueryWrapper<TtChargingLog> wrapper = new LambdaQueryWrapper<>();
        if (consumer != null) {
            consumer.accept(wrapper);
        }
        return list(wrapper);
    }

    @Override
    public List<TtChargingLog> findByStatus(Integer status) {
        return listWrapper(wrapper -> wrapper.eq(TtChargingLog::getStatus, status));
    }
}
