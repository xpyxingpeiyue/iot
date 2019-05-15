package com.tenet.iot.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.digest.DigestUtil;
import sun.security.provider.MD5;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class SignUtil {
    public static String sign(String characterEncoding, SortedMap<Object, Object> parameters, String accessKeySecret) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbkey = new StringBuilder();
        Set es = parameters.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        for (Object e : es) {
            Map.Entry entry = (Map.Entry) e;
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            //空值不传递，不参与签名组串
            if (null != v && !"".equals(v)) {
                sb.append(k).append("=").append(v).append("&");
                sbkey.append(k).append("=").append(v).append("&");
            }
        }
        sbkey = sbkey.append("accessKeySecret=").append(accessKeySecret);
        /* MD5加密,结果转换为大写字符 */
        String sign = DigestUtil.md5Hex(sbkey.toString(), characterEncoding).toLowerCase();
        return sb.toString() + "sign=" + sign;
    }

    public static void main(String[] args) {
        String data = "{\"page\":\"1\",\"size\":\"15\",\"plateNum\":\"粤B12345\"}";
        SortedMap<Object, Object> parameters = new TreeMap<>();
        parameters.put("accessKeyID", "asdfa154");
        parameters.put("version", "2018-06-05");
        parameters.put("commkey", "sdsfd121");
        parameters.put("data", URLUtil.encode(data, "UTF-8"));
        parameters.put("timestamp", System.currentTimeMillis());
        System.out.println(sign("UTF-8", parameters, "sfdf44444"));
    }
}
