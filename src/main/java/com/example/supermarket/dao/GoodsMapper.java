package com.example.supermarket.dao;

import com.example.supermarket.controller.model.GoodsReq;
import com.example.supermarket.entity.Goods;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> select(GoodsReq req);

    Integer count(GoodsReq req);
}