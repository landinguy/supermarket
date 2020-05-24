package com.example.supermarket.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.supermarket.config.JyConfig;
import com.example.supermarket.util.HttpUtil;
import com.example.supermarket.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author landing guy
 * @date 2020/5/23
 */
@Slf4j
@Component
public class JyClient {
    @Resource
    private JyConfig jyConfig;

    public Result request(String url, String params) {
        Result.ResultBuilder builder = Result.builder();

        try {
            url = jyConfig.getBase() + url;
            params = appendSign(params);
            String response = HttpUtil.post(url, null, params);
            if (response != null) {
                log.info("url#{},params#{},resp#{}", url, params, response);
                JSONObject jo = JSON.parseObject(response);
                String status = jo.getString("status");
                if (status.equals("0000")) {
                    builder.data(jo.getString("data"));
                } else {
                    builder.code(-1).msg(jo.getString("codemsg"));
                }
            } else {
                builder.code(-1).msg("请求建业api失败");
                log.info("请求建业api失败,url#{},params#{}", url, params);
            }
        } catch (Exception e) {
            e.printStackTrace();
            builder.code(-1).msg("请求建业api失败");
        }
        return builder.build();
    }

    private String appendSign(String params) throws Exception {
//        TreeMap<String, Object> treeMap = new TreeMap<>();
//        addCommonFields(treeMap);
//        Map<String, Object> map = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
//        });
//
//        List<String> kv = new ArrayList<>();
//        map.forEach((k, v) -> {
//            if (v instanceof String) {
//                String value = (String) v;
//                if (!value.equals("")) treeMap.put(k, v);
//            } else if (v != null) {
//                treeMap.put(k, v);
//            }
//        });
//        treeMap.forEach((k, v) -> kv.add(k + "=" + v));
//        kv.add("key=" + jyConfig.getKey());
//
//        String join = String.join("&", kv);
//        String sign = DigestUtils.md5DigestAsHex(join.getBytes());
//        log.info("join#{},sign#{}", join, sign);
//        treeMap.put("sign", sign);
//        String str = JSON.toJSONString(treeMap);
//        log.info("jsonStr#{}", str);
//        String data = EncryptUtil.encode(str);
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("data", "6PJG2ZZNnAQRLmApc+RRtndXNXDvoAMSudhj63ZxrAvNEhO6Yrpn8SzK9cwEetSDbKbukYcgA8VCn5pjGzEvpV2SXNb8kjS+Tb7SjlzukAB1C1PzTPKmM2CIZBbWiMFRaUbStJOe4dcK2pD3+u444trwTTc37Y5LAQ4lef0x23UpbbST9gkzZwPpOrDtmuQSuxL9TfN00/bB5neP/wOxD+ktQjDyuMJe/Uz38+xeICo7C/RFhQnvu+oALRNiV/X14hMNfgm6wxco1o+Bydq9h2borxXFpSUTOZY/19mo2qkqlZYnPTIbgCDGCiOsynb/bwsHMtWl3nOgaGcnVen2y4W2DTDsRz/zUMxNMFSqWVTdyhdh67/sGKm8xW25hGUUj7gZs/TCnOZ8V0TeKDjA3glALOwS/pdRIWc6HpATs+VJANlexWkxuPvJfEH7nhhCnUqOBsVb2duWzj8S6r3fSoefVQz21Ku/3mI0Ko8z1l3nO+UuWtC7DNA0ka/MMiQ+2g43itZ255Q=");
//        log.info("data#{},dataMap#{}", data, dataMap);
        log.info("dataMap#{}", dataMap);
        return JSON.toJSONString(dataMap);
    }

    private void addCommonFields(TreeMap<String, Object> map) {
//        String now = LocalDateTime.now().withNano(0).toString().replace("-", "").replace(":", "").replace("T", "");
//        map.put("appId", jyConfig.getAppId());
//        map.put("requestTime", now);
//        map.put("signType", "md5");
//        map.put("version", "1.0");


        map.put("appId", "rUNe1v0s9G");
        map.put("callbackUrl", "http://b2b.hnsjlh.com/jyzf_cb.html");
        map.put("outOrderAmount", 2);
        map.put("outOrderId", "032019122610483");
        map.put("outRefundNo", "032019122610382");
        map.put("outRefundTime", "20191226");
        map.put("refundFee", 2);
        map.put("refundInfo", "20191226");
        JSONObject refundInfo = new JSONObject(true);
        refundInfo.put("type", "app_code");
        refundInfo.put("payableAmount", 2);
        refundInfo.put("amount", 2);
        map.put("refundInfo", refundInfo);
        map.put("requestTime", "20191226");
        map.put("signType", "md5");
        map.put("version", "1.0");


    }
}
