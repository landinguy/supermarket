package com.example.supermarket.controller;

import com.example.supermarket.controller.model.GoodsReq;
import com.example.supermarket.entity.Goods;
import com.example.supermarket.service.GoodsService;
import com.example.supermarket.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @GetMapping("type/{name}")
    public Result addType(@PathVariable String name) {
        log.info("添加商品分类,name#{}", name);
        Result.ResultBuilder builder = Result.builder();
        try {
            goodsService.addType(name);
        } catch (Exception e) {
            builder.code(-1).msg("添加商品分类失败");
            log.error("添加商品分类失败", e);
        }
        return builder.build();
    }

    @GetMapping("types")
    public Result getType() {
        Result.ResultBuilder builder = Result.builder();
        try {
            builder.data(goodsService.getType());
        } catch (Exception e) {
            builder.code(-1).msg("查询商品分类失败");
            log.error("查询商品分类失败", e);
        }
        return builder.build();
    }

    @PostMapping("add")
    public Result addGoods(@RequestBody Goods goods) {
        log.info("添加商品,params#{}", goods);
        Result.ResultBuilder builder = Result.builder();
        try {
            goodsService.addGoods(goods);
        } catch (Exception e) {
            builder.code(-1).msg("添加商品失败");
            log.error("添加商品失败", e);
        }
        return builder.build();
    }

    @PostMapping("get")
    public Result getGoods(@RequestBody GoodsReq req) {
        log.info("查询商品,params#{}", req);
        Result.ResultBuilder builder = Result.builder();
        try {
            builder.data(goodsService.getGoods(req));
        } catch (Exception e) {
            builder.code(-1).msg("查询商品失败");
            log.error("查询商品失败", e);
        }
        return builder.build();
    }


}
