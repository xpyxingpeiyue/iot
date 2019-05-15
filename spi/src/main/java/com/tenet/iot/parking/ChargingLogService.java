package com.tenet.iot.parking;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 充电记录服务类  后期整合dubbo提供接口
 */
public interface ChargingLogService extends IService<TtChargingLog> {
    List<TtChargingLog> findByStatus(Integer status);
}
