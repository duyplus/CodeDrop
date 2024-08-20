package com.codedrop.controller;

import com.codedrop.model.BankAmount;
import com.codedrop.service.BankAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/bank_amount")
public class BankAmountController {

    @Autowired
    BankAmountService bankAmountService;

    @GetMapping
    public ResponseEntity<List<BankAmount>> getAll() {
        List<BankAmount> item = bankAmountService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<BankAmount>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<BankAmount>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BankAmount> getOne(@PathVariable("id") int id) {
        BankAmount item = bankAmountService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public BankAmount create(@RequestBody BankAmount item) {
        return bankAmountService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<BankAmount> update(@PathVariable("id") int id, @RequestBody BankAmount item) {
        BankAmount existing = bankAmountService.findById(id);
        bankAmountService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        BankAmount item = bankAmountService.findById(id);
        bankAmountService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
