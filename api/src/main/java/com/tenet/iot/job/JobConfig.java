package com.tenet.iot.job;

import cn.hutool.cron.CronUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author xing
 */
@Component
public class JobConfig implements ApplicationRunner, Ordered {
    private static final String PATTERN_MIN_1 = "*/1 * * * *";//60s
    private static final String PATTERN_MIN_5 = "*/5 * * * *";//300s

    @Override
    public void run(ApplicationArguments args) {
        // 每300s 从chargging表中获取数据
        CronUtil.schedule(PATTERN_MIN_5, new ChargingOrderJob());
        // 每60s 按充电单号到charging表中获取状态，并推送
        CronUtil.schedule(PATTERN_MIN_1, new ChargingStatusJob());
        CronUtil.start(); // 开始执行
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE; // 最后执行
    }
}
