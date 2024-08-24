package com.codedrop.service.impl;

import com.codedrop.common.CustomSpecification;
import com.codedrop.model.OrderDetail;
import com.codedrop.repository.OrderDetailRepository;
import com.codedrop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }

    public Page<OrderDetail> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderDetailRepository.findAll(pageable);
    }

    public Page<OrderDetail> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<OrderDetail> specification = CustomSpecification.createSpecification(conditions);
        return orderDetailRepository.findAll(specification, pageable);
    }

    @Override
    public OrderDetail findById(Integer id) {
        return orderDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderDetail not found with id " + id));
    }

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void delete(OrderDetail orderDetail) {
        orderDetailRepository.delete(orderDetail);
    }
}
