package com.codedrop.controller;

import com.codedrop.model.SourceType;
import com.codedrop.service.SourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/source_type", produces = "application/json;charset=UTF-8")
public class SourceTypeController {

    @Autowired
    SourceTypeService sourceTypeService;

    @GetMapping
    public ResponseEntity<List<SourceType>> getAll() {
        List<SourceType> item = sourceTypeService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<SourceType>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<SourceType>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SourceType> getOne(@PathVariable("id") int id) {
        SourceType item = sourceTypeService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public SourceType create(@RequestBody SourceType item) {
        return sourceTypeService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<SourceType> update(@PathVariable("id") int id, @RequestBody SourceType item) {
        SourceType existing = sourceTypeService.findById(id);
        sourceTypeService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        SourceType item = sourceTypeService.findById(id);
        sourceTypeService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
