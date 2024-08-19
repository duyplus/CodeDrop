package com.codedrop.service;

import com.codedrop.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetail> findAll();

    OrderDetail findById(Integer id);

    OrderDetail create(OrderDetail orderDetail);

    OrderDetail update(OrderDetail orderDetail);

    void delete(OrderDetail orderDetail);
}
