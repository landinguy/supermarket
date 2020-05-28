package com.example.supermarket.service;

import com.alibaba.fastjson.JSON;
import com.example.supermarket.client.JyClient;
import com.example.supermarket.config.JyConfig;
import com.example.supermarket.controller.model.BillReq;
import com.example.supermarket.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class BillService {
    @Resource
    private CommonService commonService;
    @Resource
    private JyClient jyClient;
    @Resource
    private JyConfig jyConfig;


    public Result get(String date) {
        Map<String, Object> map = new HashMap<>();
        map.put("placedDate", date);
        Result result = jyClient.request(jyConfig.getDailyBill(), JSON.toJSONString(map));
        Optional.ofNullable(result.getData()).ifPresent(it -> result.setData(JSON.parseArray((String) it)));
        return result;
    }

    public Result add(BillReq req) {
        Map<String, Object> map = new HashMap<>();
        map.put("placedDate", LocalDate.now().minusDays(1).toString());
        map.put("Info", JSON.toJSONString(req));
        return jyClient.request(jyConfig.getAddBill(), JSON.toJSONString(map));
    }

    public Result getTransaction(String outOrderId, Integer outOrderAmount) {
        Map<String, Object> map = new HashMap<>();
        map.put("outOrderId", outOrderId);
        map.put("outOrderAmount", outOrderAmount);
        return jyClient.request(jyConfig.getQueryPay(), JSON.toJSONString(map));
    }
}