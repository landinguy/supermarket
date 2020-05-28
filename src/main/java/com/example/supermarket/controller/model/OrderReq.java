
package com.example.supermarket.controller.model;

import lombok.Data;

@Data
public class OrderReq {
    private Integer pageNo;
    private Integer pageSize;
    private Integer userId;
}