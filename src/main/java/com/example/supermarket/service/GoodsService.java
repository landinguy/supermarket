package com.example.supermarket.service;

import com.alibaba.fastjson.JSONObject;
import com.example.supermarket.controller.model.GoodsReq;
import com.example.supermarket.dao.GoodsMapper;
import com.example.supermarket.dao.GoodsTypeMapper;
import com.example.supermarket.entity.Goods;
import com.example.supermarket.entity.GoodsType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class GoodsService {
    @Resource
    private GoodsTypeMapper goodsTypeMapper;
    @Resource
    private GoodsMapper goodsMapper;

    public void addType(String name) {
        goodsTypeMapper.insertSelective(GoodsType.builder().name(name).build());
    }

    public Object getType() {
        return goodsTypeMapper.select();
    }

    public void addGoods(Goods goods) {
        goodsMapper.insertSelective(goods);
    }

    public Object getGoods(GoodsReq req) {
        Integer total = goodsMapper.count(req);
        List<Goods> list = goodsMapper.select(req);
        JSONObject data = new JSONObject();
        data.put("total", total);
        data.put("list", list);
        return data;
    }
}
