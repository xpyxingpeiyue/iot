package com.tenet.iot.module.thirdpart.charging;

import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.tenet.iot.module.thirdpart.CommonService;
import feign.Feign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CharingIntService extends CommonService {
    private static final String URL = "https://openapi.deliyun.cn/parking/";
    private static final Log log = LogFactory.get();
    @Value("${charging.accessKeyID}")
    private String accessKeyID;
    @Value("${charging.version}")
    private String version;
    @Value("${charging.commkey}")
    private String commkey;
    @Value("${charging.accessKeySecret}")
    private String accessKeySecret;

    public String uploadChargingLog(JSONObject params) {
        log.info("充电记录上传参数：{}", params);
        String signParams = sign(accessKeyID, version, commkey, params.toString(), accessKeySecret);
        ChargeFeign chargeFeign = Feign.builder()
                .target(ChargeFeign.class, URL + "uploadChargingLog?" + signParams);
        String str = chargeFeign.pushChargingLog(URLUtil.encode(params.toString(), "UTF-8"));
        return str;
    }

}
