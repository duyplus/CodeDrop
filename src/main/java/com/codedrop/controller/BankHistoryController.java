package com.codedrop.controller;

import com.codedrop.common.CustomPage;
import com.codedrop.common.CustomQueryParsing;
import com.codedrop.model.BankAmount;
import com.codedrop.model.BankHistory;
import com.codedrop.service.BankHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/bank_history")
public class BankHistoryController {

    @Autowired
    BankHistoryService bankHistoryService;

    @Autowired
    private CustomQueryParsing customQueryParsing;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String query) {
        if (query == null) {
            List<BankHistory> items = bankHistoryService.findAll();
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
                Page<BankHistory> paginatedUsers = bankHistoryService.findPaginateWithConditions(page, size, conditions);
                CustomPage<BankHistory> customPage = new CustomPage<>(paginatedUsers);
                return new ResponseEntity<>(customPage, HttpStatus.OK);
            } catch (CustomQueryParsing.InvalidQueryFormatException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
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
