package com.codedrop.controller;

import com.codedrop.model.CommentSource;
import com.codedrop.service.CommentSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/comment_source")
public class CommentSourceController {

    @Autowired
    CommentSourceService commentSourceService;

    @GetMapping
    public ResponseEntity<List<CommentSource>> getAll() {
        List<CommentSource> item = commentSourceService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<CommentSource>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CommentSource>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentSource> getOne(@PathVariable("id") int id) {
        CommentSource item = commentSourceService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public CommentSource create(@RequestBody CommentSource item) {
        return commentSourceService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<CommentSource> update(@PathVariable("id") int id, @RequestBody CommentSource item) {
        CommentSource existing = commentSourceService.findById(id);
        commentSourceService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        CommentSource item = commentSourceService.findById(id);
        commentSourceService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
