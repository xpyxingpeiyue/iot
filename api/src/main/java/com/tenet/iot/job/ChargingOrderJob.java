package com.tenet.iot.job;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.tenet.iot.enums.ChargeLogEnum;
import com.tenet.iot.parking.ChargingLogService;
import com.tenet.iot.parking.ChargingService;
import com.tenet.iot.parking.TtCharging;
import com.tenet.iot.parking.TtChargingLog;
import com.tenet.iot.util.SpringContextUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ChargingOrderJob implements Runnable {
    private static final Log log = LogFactory.get();
    private static final ChargingService chargingService = SpringContextUtil.getBean(ChargingService.class);
    private static final ChargingLogService chargingLogService = SpringContextUtil.getBean(ChargingLogService.class);

    @Override
    public void run() {
        //获取指定充电站点的充电单号
        //获取充电状态的记录
        List<TtCharging> ttChargingList = chargingService.findByStatus(ChargeLogEnum.CHARGE_RUNNING.getValue());
        List<TtChargingLog> ttChargingLogs = ttChargingList.stream().map(t -> {
            TtChargingLog ttChargingLog = new TtChargingLog();
            ttChargingLog.setChargeId(t.getId());
            ttChargingLog.setStatus(t.getStatus());
            return ttChargingLog;
        }).collect(Collectors.toList());
        //充电状态入库
        chargingLogService.saveBatch(ttChargingLogs);

    }

}
