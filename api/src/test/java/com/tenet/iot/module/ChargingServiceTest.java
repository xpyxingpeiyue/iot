package com.tenet.iot.module;

import com.tenet.iot.BaseServiceTest;
import com.tenet.iot.enums.ChargeLogEnum;
import com.tenet.iot.parking.ChargingService;
import com.tenet.iot.parking.TtCharging;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;


public class ChargingServiceTest extends BaseServiceTest {
    @Resource
    private ChargingService chargingService;

    @Test
    public void save() {
        TtCharging ttCharging = new TtCharging();
        ttCharging.setCode("iot-145114");
        ttCharging.setStartTime(new Date());
        ttCharging.setSiteId("tt19");
        ttCharging.setPileId("dd48412114");
        ttCharging.setMemberId("xing478546595477");
        ttCharging.setEndTime(new Date());
        ttCharging.setStatus(ChargeLogEnum.CHARGE_RUNNING.getValue());
        ttCharging.setChargeTime(1);
        chargingService.save(ttCharging);
    }
}
