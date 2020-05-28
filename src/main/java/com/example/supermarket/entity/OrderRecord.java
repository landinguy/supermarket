package com.example.supermarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String createTs;
    private Integer status;// 1，待支付 2. 已支付 3. 已完成 4. 已失效
    private String goodsId;
    private Integer userId;
    private Float price;
    private String payTs;
    private String address;
}