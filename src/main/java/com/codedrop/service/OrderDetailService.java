package com.codedrop.service;

import com.codedrop.model.OrderDetail;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface OrderDetailService {

    List<OrderDetail> findAll();

    Page<OrderDetail> findPaginate(int page, int size);

    Page<OrderDetail> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    OrderDetail findById(Integer id);

    OrderDetail create(OrderDetail orderDetail);

    OrderDetail update(OrderDetail orderDetail);

    void delete(OrderDetail orderDetail);
}
