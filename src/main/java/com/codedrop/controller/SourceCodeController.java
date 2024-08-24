package com.codedrop.controller;

import com.codedrop.common.CustomPage;
import com.codedrop.common.CustomQueryParsing;
import com.codedrop.model.SourceCode;
import com.codedrop.service.SourceCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/source_code")
public class SourceCodeController {

    @Autowired
    SourceCodeService sourceCodeService;

    @Autowired
    private CustomQueryParsing customQueryParsing;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String query) {
        if (query == null) {
            List<SourceCode> items = sourceCodeService.findAll();
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
                Page<SourceCode> paginatedUsers = sourceCodeService.findPaginateWithConditions(page, size, conditions);
                CustomPage<SourceCode> customPage = new CustomPage<>(paginatedUsers);
                return new ResponseEntity<>(customPage, HttpStatus.OK);
            } catch (CustomQueryParsing.InvalidQueryFormatException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
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
