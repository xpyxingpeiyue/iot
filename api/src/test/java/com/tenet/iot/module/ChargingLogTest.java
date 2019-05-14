package com.tenet.iot.module;

import com.tenet.iot.BaseServiceTest;
import com.tenet.iot.enums.ChargeLogEnum;
import com.tenet.iot.parking.ChargingLogService;
import com.tenet.iot.parking.TtChargingLog;
import org.junit.Test;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;


public class ChargingLogTest extends BaseServiceTest {
    @Resource
    private ChargingLogService chargingLogService;

    @Test
    public void save() {
        TtChargingLog ttChargingLog = new TtChargingLog();
        ttChargingLog.setCode("iot-tenet");
        ttChargingLog.setPlateNum("ts789456");
        ttChargingLog.setChargeStartTime(new Date());
        ttChargingLog.setChargeEndTime(new Date());
        ttChargingLog.setChargeTime(50);
        ttChargingLog.setChargeMoney(BigDecimal.valueOf(21.50));
        ttChargingLog.setChargeEle(BigDecimal.valueOf(109.12));
        ttChargingLog.setEleMeterNumBefore(BigDecimal.valueOf(1121.1));
        ttChargingLog.setEleMeterNumAfter(BigDecimal.valueOf(1954.3));
        ttChargingLog.setEleType(ChargeLogEnum.DIRECT.getValue());
        ttChargingLog.setChargeCardNum("123456789");
        ttChargingLog.setChargeType(ChargeLogEnum.CHARGE_FULL.getValue());
        ttChargingLog.setChargeValue(BigDecimal.valueOf(15));
        ttChargingLog.setChargeSerNum("iot7456123111454");
        chargingLogService.save(ttChargingLog);
    }
}
