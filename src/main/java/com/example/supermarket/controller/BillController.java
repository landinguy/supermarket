package com.example.supermarket.controller;

import com.example.supermarket.controller.model.BillReq;
import com.example.supermarket.service.BillService;
import com.example.supermarket.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("bill")
public class BillController {
    @Resource
    private BillService billService;

    @GetMapping("get")
    public Result get(String date) {
        log.info("查询服务商日账单,date#{}", date);
        Result.ResultBuilder builder = Result.builder();
        try {
            return billService.get(date);
        } catch (Exception e) {
            builder.code(-1).msg("查询服务商日账单失败");
            log.error("查询服务商日账单失败", e);
        }
        return builder.build();
    }

    @PostMapping("add")
    public Result add(@RequestBody BillReq req) {
        log.info("商户对账,params#{}", req);
        Result.ResultBuilder builder = Result.builder();
        try {
            return billService.add(req);
        } catch (Exception e) {
            builder.code(-1).msg("商户对账失败");
            log.error("商户对账失败", e);
        }
        return builder.build();
    }

}