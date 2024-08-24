package com.codedrop.controller;

import com.codedrop.common.CustomPage;
import com.codedrop.common.CustomQueryParsing;
import com.codedrop.model.Report;
import com.codedrop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @Autowired
    private CustomQueryParsing customQueryParsing;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String query) {
        if (query == null) {
            List<Report> items = reportService.findAll();
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
                Page<Report> paginatedUsers = reportService.findPaginateWithConditions(page, size, conditions);
                CustomPage<Report> customPage = new CustomPage<>(paginatedUsers);
                return new ResponseEntity<>(customPage, HttpStatus.OK);
            } catch (CustomQueryParsing.InvalidQueryFormatException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Report> getOne(@PathVariable("id") int id) {
        Report item = reportService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public Report create(@RequestBody Report item) {
        return reportService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<Report> update(@PathVariable("id") int id, @RequestBody Report item) {
        Report existing = reportService.findById(id);
        reportService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Report item = reportService.findById(id);
        reportService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
