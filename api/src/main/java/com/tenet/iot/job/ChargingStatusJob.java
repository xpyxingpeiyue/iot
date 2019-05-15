package com.tenet.iot.job;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tenet.iot.enums.ChargeLogEnum;
import com.tenet.iot.lock.SyncLockService;
import com.tenet.iot.module.thirdpart.charging.CharingIntService;
import com.tenet.iot.parking.ChargingLogService;
import com.tenet.iot.parking.ChargingService;
import com.tenet.iot.parking.TtCharging;
import com.tenet.iot.parking.TtChargingLog;
import com.tenet.iot.system.MemberService;
import com.tenet.iot.system.TtMember;
import com.tenet.iot.util.SpringContextUtil;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChargingStatusJob implements Runnable {
    private static final Log log = LogFactory.get();
    private static final ChargingLogService chargingLogService = SpringContextUtil.getBean(ChargingLogService.class);
    private static final ChargingService chargingService = SpringContextUtil.getBean(ChargingService.class);
    private static final MemberService memberService = SpringContextUtil.getBean(MemberService.class);

    private static final CharingIntService chargingIntService = SpringContextUtil.getBean(CharingIntService.class);
    private static final SyncLockService syncLock = SpringContextUtil.getBean(SyncLockService.class);

    private static final String LOCK_CODE = "charge_status_job";

    private static final ExecutorService POOL = Executors.newFixedThreadPool(5);

    @Override
    public void run() {
        //多个服务只有一个服务启动
        if (syncLock.lock(LOCK_CODE)) {
            try {
                //查询状态为充电中的状态
                List<TtChargingLog> ttChargingLogs = chargingLogService.findByStatus(ChargeLogEnum.CHARGE_RUNNING.getValue());
                ttChargingLogs.forEach(t -> POOL.execute(() -> {
                    //获取充电记录中的状态
                    TtCharging ttCharging = chargingService.getById(t.getChargeId());
                    //获取状态为充满的
                    if (ttCharging.getStatus() == ChargeLogEnum.CHARGE_FULL.getValue()) {
                        //修改自建表中的值
                        TtChargingLog ttChargingLog = new TtChargingLog();
                        ttChargingLog.setStatus(ChargeLogEnum.CHARGE_FULL.getValue());
                        UpdateWrapper<TtChargingLog> updateWrapper = new UpdateWrapper<>();
                        updateWrapper.eq("id", t.getId());
                        updateWrapper.eq("status", ChargeLogEnum.CHARGE_RUNNING.getValue());
                        //update set where 防止多个并发同时请求更改这条数据
                        pushData(chargingLogService.update(ttChargingLog, updateWrapper), ttCharging);
                    }
                }));
            } catch (Exception e) {
                log.error(e);
            } finally {
                syncLock.unlock(LOCK_CODE);
            }

        }


    }

    /**
     * push数据
     */
    private void pushData(boolean flag, TtCharging ttCharging) {
        if (flag) {
            //充电完成状态修改成功后，判断是否一天内多次充电当天内00:00:00 23:59:59
            //获取会员信息,根据会员信息获取一天的充电记录，并且状态为充电完成的
            int count = chargingService.findByMemberAndDate(ttCharging.getMemberId(), ChargeLogEnum.CHARGE_FULL.getValue(), DateUtil.format(new Date(), "yyyy-MM-dd 00:00:00"), DateUtil.format(new Date(), "yyyy-MM-dd 23:59:59"));
            if (count == 1) {
                //推送
                TtMember ttMember = memberService.findByMemberId(ttCharging.getMemberId());
                JSONObject params = JSONUtil.createObj();
                params.put("code", ttCharging.getCode());
                params.put("plateNum", ttMember.getPlateNum());
                params.put("chargeStartTime", ttCharging.getStartTime().getTime());
                params.put("chargeEndTime", ttCharging.getEndTime().getTime());
                params.put("chargeTime", ttCharging.getChargeTime());
                //接口请求数据
                chargingIntService.uploadChargingLog(params);
            }

        }
    }

}
