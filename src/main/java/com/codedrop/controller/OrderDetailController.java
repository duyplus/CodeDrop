package com.codedrop.controller;

import com.codedrop.common.CustomPage;
import com.codedrop.common.CustomQueryParsing;
import com.codedrop.model.OrderDetail;
import com.codedrop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/order_detail")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    private CustomQueryParsing customQueryParsing;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String query) {
        if (query == null) {
            List<OrderDetail> items = orderDetailService.findAll();
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } else {
            try {
                CustomQueryParsing.ParsedQuery parsedQuery = customQueryParsing.safeParseQuery(query);
                Map<String, String> conditions = parsedQuery.getConditions();
                int page = parsedQuery.getPage();
                int size = parsedQuery.getSize();
                Page<OrderDetail> paginatedUsers = orderDetailService.findPaginateWithConditions(page, size, conditions);
                CustomPage<OrderDetail> customPage = new CustomPage<>(paginatedUsers);
                return new ResponseEntity<>(customPage, HttpStatus.OK);
            } catch (CustomQueryParsing.InvalidQueryFormatException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
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
