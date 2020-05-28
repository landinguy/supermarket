package com.example.supermarket.controller;

import com.example.supermarket.service.BillService;
import com.example.supermarket.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("transaction")
public class TransactionController {
    @Resource
    private BillService billService;

    @GetMapping("get")
    public Result get(String outOrderId, Integer outOrderAmount) {
        log.info("查询交易,outOrderId#{},outOrderAmount#{}", outOrderId, outOrderAmount);
        Result.ResultBuilder builder = Result.builder();
        try {
            return billService.getTransaction(outOrderId, outOrderAmount);
        } catch (Exception e) {
            builder.code(-1).msg("查询交易失败");
            log.error("查询交易失败", e);
        }
        return builder.build();
    }

}
