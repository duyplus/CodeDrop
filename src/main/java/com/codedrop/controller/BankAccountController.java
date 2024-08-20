package com.codedrop.controller;

import com.codedrop.model.BankAccount;
import com.codedrop.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/bank_account")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping
    public ResponseEntity<List<BankAccount>> getAll() {
        List<BankAccount> item = bankAccountService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<BankAccount>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<BankAccount>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BankAccount> getOne(@PathVariable("id") String id) {
        BankAccount item = bankAccountService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public BankAccount create(@RequestBody BankAccount item) {
        return bankAccountService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<BankAccount> update(@PathVariable("id") String id, @RequestBody BankAccount item) {
        BankAccount existing = bankAccountService.findById(id);
        bankAccountService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        BankAccount item = bankAccountService.findById(id);
        bankAccountService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
