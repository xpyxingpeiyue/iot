package com.tenet.iot.module.thirdpart.charging;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ChargeFeign {
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestLine("POST")
    String pushChargingLog(@Param("data") String data);
}
