package com.codedrop.controller;

import com.codedrop.model.Comment;
import com.codedrop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAll() {
        List<Comment> item = commentService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Comment>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getOne(@PathVariable("id") int id) {
        Comment item = commentService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public Comment create(@RequestBody Comment item) {
        return commentService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<Comment> update(@PathVariable("id") int id, @RequestBody Comment item) {
        Comment existing = commentService.findById(id);
        commentService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Comment item = commentService.findById(id);
        commentService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
