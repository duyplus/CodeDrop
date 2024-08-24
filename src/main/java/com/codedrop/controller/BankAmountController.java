package com.codedrop.controller;

import com.codedrop.common.CustomPage;
import com.codedrop.common.CustomQueryParsing;
import com.codedrop.model.BankAmount;
import com.codedrop.service.BankAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/bank_amount")
public class BankAmountController {

    @Autowired
    BankAmountService bankAmountService;

    @Autowired
    private CustomQueryParsing customQueryParsing;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String query) {
        if (query == null) {
            List<BankAmount> items = bankAmountService.findAll();
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
                Page<BankAmount> paginatedUsers = bankAmountService.findPaginateWithConditions(page, size, conditions);
                CustomPage<BankAmount> customPage = new CustomPage<>(paginatedUsers);
                return new ResponseEntity<>(customPage, HttpStatus.OK);
            } catch (CustomQueryParsing.InvalidQueryFormatException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
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
