package com.example.supermarket.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.supermarket.config.JyConfig;
import com.example.supermarket.util.HttpUtil;
import com.example.supermarket.util.PasswordUtil;
import com.example.supermarket.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;

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
                JSONObject jo = JSON.parseObject(response);
                String status = jo.getString("status");
                if (status.equals("0000")) {
                    builder.data(jo.getString("data"));
                } else {
                    builder.code(-1).msg(jo.getString("codemsg"));
                    log.info("url#{},params#{},resp#{}", url, params, response);
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
        TreeMap<String, Object> treeMap = new TreeMap<>();
        addCommonFields(treeMap);
        Map<String, Object> map = JSON.parseObject(params, new TypeReference<Map<String, Object>>() {
        });

        List<String> kv = new ArrayList<>();
        map.forEach((k, v) -> {
            if (v instanceof String) {
                String value = (String) v;
                if (!value.equals("")) treeMap.put(k, v);
            } else if (v != null) {
                treeMap.put(k, v);
            }
        });
        treeMap.forEach((k, v) -> kv.add(k + "=" + v));
        kv.add("key=" + jyConfig.getKey());

        String join = String.join("&", kv);
        String sign = DigestUtils.md5DigestAsHex(join.getBytes());
        log.info("join#{},sign#{}", join, sign);

        LinkedHashMap<String, Object> linkedHashMap = JSON.parseObject(JSON.toJSONString(treeMap), new TypeReference<LinkedHashMap<String, Object>>() {
        });
        linkedHashMap.put("sign", sign);

        String paramsStr = JSON.toJSONString(linkedHashMap);
        String data = PasswordUtil.encrypt3DES(paramsStr.getBytes(), jyConfig.getDesKey().getBytes());
        log.info("paramsStr#{},data#{}", paramsStr, data);
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("data", data);
        return JSON.toJSONString(dataMap);
    }

    private void addCommonFields(TreeMap<String, Object> map) {
        String now = LocalDate.now().toString();
        map.put("appId", jyConfig.getAppId());
        map.put("requestTime", now);
        map.put("signType", "md5");
        map.put("version", "1.0");
    }
}
