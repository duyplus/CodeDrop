package com.codedrop.controller;

import com.codedrop.model.BankHistory;
import com.codedrop.service.BankHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/bank_history")
public class BankHistoryController {

    @Autowired
    BankHistoryService bankHistoryService;

    @GetMapping
    public ResponseEntity<List<BankHistory>> getAll() {
        List<BankHistory> item = bankHistoryService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<BankHistory>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<BankHistory>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BankHistory> getOne(@PathVariable("id") int id) {
        BankHistory item = bankHistoryService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public BankHistory create(@RequestBody BankHistory item) {
        return bankHistoryService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<BankHistory> update(@PathVariable("id") int id, @RequestBody BankHistory item) {
        BankHistory existing = bankHistoryService.findById(id);
        bankHistoryService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        BankHistory item = bankHistoryService.findById(id);
        bankHistoryService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
