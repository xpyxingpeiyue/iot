package com.tenet.iot.parking.charging;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tenet.iot.mapper.ChargingLogMapper;
import com.tenet.iot.parking.ChargingLogService;
import com.tenet.iot.parking.TtChargingLog;
import org.springframework.stereotype.Service;

/**
 * 充电记录实现类
 */
@Service
public class ChargingLogServiceImpl extends ServiceImpl<ChargingLogMapper, TtChargingLog> implements ChargingLogService {
}
