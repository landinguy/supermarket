package com.example.supermarket.controller;

import com.example.supermarket.controller.model.OrderReq;
import com.example.supermarket.entity.OrderRecord;
import com.example.supermarket.service.OrderService;
import com.example.supermarket.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @PostMapping("add")
    public Result add(@RequestBody OrderRecord record) {
        log.info("生成订单,params#{}", record);
        Result.ResultBuilder builder = Result.builder();
        try {
            orderService.add(record);
        } catch (Exception e) {
            builder.code(-1).msg("生成订单失败");
            log.error("生成订单失败", e);
        }
        return builder.build();
    }

    @PostMapping("get")
    public Result get(@RequestBody OrderReq req) {
        log.info("查询订单,params#{}", req);
        Result.ResultBuilder builder = Result.builder();
        try {
            builder.data(orderService.get(req));
        } catch (Exception e) {
            builder.code(-1).msg("查询订单失败");
            log.error("查询订单失败", e);
        }
        return builder.build();
    }

}
