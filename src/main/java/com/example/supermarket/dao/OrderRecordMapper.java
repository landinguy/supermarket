package com.example.supermarket.dao;

import com.example.supermarket.controller.model.OrderReq;
import com.example.supermarket.entity.OrderRecord;

import java.util.List;

public interface OrderRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRecord record);

    int insertSelective(OrderRecord record);

    OrderRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRecord record);

    int updateByPrimaryKey(OrderRecord record);

    List<OrderRecord> select(OrderReq req);

    Integer count(OrderReq req);

}