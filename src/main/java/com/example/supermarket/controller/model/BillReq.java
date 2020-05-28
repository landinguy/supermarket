package com.example.supermarket.controller.model;

import lombok.Data;

@Data
public class BillReq {
    private Integer outid;
    private String pay_amount;
    private Integer pay_count;
    private String refund_amount;
    private Integer refund_count;
}