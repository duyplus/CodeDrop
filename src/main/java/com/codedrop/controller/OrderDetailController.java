package com.codedrop.controller;

import com.codedrop.model.OrderDetail;
import com.codedrop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/order_detail")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAll() {
        List<OrderDetail> item = orderDetailService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<OrderDetail>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<OrderDetail>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDetail> getOne(@PathVariable("id") int id) {
        OrderDetail item = orderDetailService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public OrderDetail create(@RequestBody OrderDetail item) {
        return orderDetailService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderDetail> update(@PathVariable("id") int id, @RequestBody OrderDetail item) {
        OrderDetail existing = orderDetailService.findById(id);
        orderDetailService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        OrderDetail item = orderDetailService.findById(id);
        orderDetailService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
