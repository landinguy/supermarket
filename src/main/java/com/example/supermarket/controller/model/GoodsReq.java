package com.example.supermarket.controller.model;

import lombok.Data;

@Data
public class GoodsReq {
    private Integer pageNo;
    private Integer pageSize;
    private Integer type;
}