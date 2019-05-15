package com.tenet.iot.parking.charging;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tenet.iot.mapper.ChargingMapper;
import com.tenet.iot.parking.ChargingService;
import com.tenet.iot.parking.TtCharging;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

/**
 * 充电记录实现类
 */
@Service
public class ChargingServiceImpl extends ServiceImpl<ChargingMapper, TtCharging> implements ChargingService {
    @Override
    public List<TtCharging> findByStatus(Integer status) {
        return listWrapper(wrapper -> wrapper.eq(TtCharging::getStatus, status));
    }

    @Override
    public int findByMemberAndDate(String memberId, Integer status, String startTime, String endTime) {
        return countWrapper(wrapper -> wrapper.eq(TtCharging::getMemberId, memberId)
                .eq(TtCharging::getStatus, status)
                .ge(TtCharging::getEndTime, startTime)
                .le(TtCharging::getEndTime, endTime));
    }

    private int countWrapper(Consumer<LambdaQueryWrapper<TtCharging>> consumer) {
        LambdaQueryWrapper<TtCharging> wrapper = new LambdaQueryWrapper<>();
        if (consumer != null) {
            consumer.accept(wrapper);
        }
        return count(wrapper);
    }

    private List<TtCharging> listWrapper(Consumer<LambdaQueryWrapper<TtCharging>> consumer) {
        LambdaQueryWrapper<TtCharging> wrapper = new LambdaQueryWrapper<>();
        if (consumer != null) {
            consumer.accept(wrapper);
        }
        return list(wrapper);
    }
}
