package com.codedrop.controller;

import com.codedrop.model.Report;
import com.codedrop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping
    public ResponseEntity<List<Report>> getAll() {
        List<Report> item = reportService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<Report>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Report>>(item, HttpStatus.OK);
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
