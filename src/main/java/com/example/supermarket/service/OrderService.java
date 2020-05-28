package com.example.supermarket.service;

import com.alibaba.fastjson.JSONObject;
import com.example.supermarket.controller.model.OrderReq;
import com.example.supermarket.dao.OrderRecordMapper;
import com.example.supermarket.entity.OrderRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {
    @Resource
    private OrderRecordMapper orderRecordMapper;
    @Resource
    private CommonService commonService;
    @Resource
    private UserService userService;

    public void add(OrderRecord record) {
        OrderRecord build = OrderRecord.builder().number(commonService.getUUID()).createTs(commonService.getDateTime()).userId(commonService.getUserId())
                .status(3).goodsId(record.getGoodsId()).price(record.getPrice()).address(record.getAddress()).build();
        orderRecordMapper.insertSelective(build);
    }

    public Object get(OrderReq req) {
        Integer userId = commonService.getUserId();
        Optional.ofNullable(userService.getById(userId)).ifPresent(user -> {
            if (!user.getRole().equals("ADMIN")) req.setUserId(userId);
        });
        Integer total = orderRecordMapper.count(req);
        List<OrderRecord> list = orderRecordMapper.select(req);
        JSONObject data = new JSONObject();
        data.put("total", total);
        data.put("list", list);
        return data;
    }
}
