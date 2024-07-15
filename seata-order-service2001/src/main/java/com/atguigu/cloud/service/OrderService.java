package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Order;

public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);

}