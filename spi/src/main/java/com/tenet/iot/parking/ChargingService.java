package com.tenet.iot.parking;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * 充电记录服务类  后期整合dubbo提供接口
 */
public interface ChargingService extends IService<TtCharging> {
    List<TtCharging> findByStatus(Integer status);

    int findByMemberAndDate(String memberId, Integer status, String startTime, String endTime);
}
