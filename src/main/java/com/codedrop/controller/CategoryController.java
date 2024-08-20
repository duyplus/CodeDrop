package com.codedrop.controller;

import com.codedrop.model.Category;
import com.codedrop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> item = categoryService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getOne(@PathVariable("id") int id) {
        Category item = categoryService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public Category create(@RequestBody Category item) {
        return categoryService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> update(@PathVariable("id") int id, @RequestBody Category item) {
        Category existing = categoryService.findById(id);
        categoryService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Category item = categoryService.findById(id);
        categoryService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
