package com.tenet.iot.module.thirdpart;

import cn.hutool.core.util.URLUtil;
import com.tenet.iot.util.SignUtil;

import java.util.SortedMap;
import java.util.TreeMap;

public class CommonService {
    protected String sign(String accessKeyID, String version, String commkey, String data, String accessKeySecret) {
        SortedMap<Object, Object> parameters = new TreeMap<>();
        parameters.put("accessKeyID", accessKeyID);
        parameters.put("version", version);
        parameters.put("commkey", commkey);
        parameters.put("data", URLUtil.encode(data,"UTF-8"));
        parameters.put("timestamp", System.currentTimeMillis());
        return SignUtil.sign("UTF-8", parameters, accessKeySecret);
    }
}
