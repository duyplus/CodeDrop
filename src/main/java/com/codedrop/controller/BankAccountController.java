package com.codedrop.controller;

import com.codedrop.common.CustomPage;
import com.codedrop.common.CustomQueryParsing;
import com.codedrop.model.BankAccount;
import com.codedrop.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/bank_account")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private CustomQueryParsing customQueryParsing;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String query) {
        if (query == null) {
            List<BankAccount> items = bankAccountService.findAll();
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
                Page<BankAccount> paginatedUsers = bankAccountService.findPaginateWithConditions(page, size, conditions);
                CustomPage<BankAccount> customPage = new CustomPage<>(paginatedUsers);
                return new ResponseEntity<>(customPage, HttpStatus.OK);
            } catch (CustomQueryParsing.InvalidQueryFormatException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
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
