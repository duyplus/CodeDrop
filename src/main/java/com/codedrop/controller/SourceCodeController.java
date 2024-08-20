package com.codedrop.controller;

import com.codedrop.model.SourceCode;
import com.codedrop.service.SourceCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/source_code")
public class SourceCodeController {

    @Autowired
    SourceCodeService sourceCodeService;

    @GetMapping
    public ResponseEntity<List<SourceCode>> getAll() {
        List<SourceCode> item = sourceCodeService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<SourceCode>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<SourceCode>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SourceCode> getOne(@PathVariable("id") int id) {
        SourceCode item = sourceCodeService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public SourceCode create(@RequestBody SourceCode item) {
        return sourceCodeService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<SourceCode> update(@PathVariable("id") int id, @RequestBody SourceCode item) {
        SourceCode existing = sourceCodeService.findById(id);
        sourceCodeService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        SourceCode item = sourceCodeService.findById(id);
        sourceCodeService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
