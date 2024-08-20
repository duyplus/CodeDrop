package com.codedrop.controller;

import com.codedrop.model.Post;
import com.codedrop.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAll() {
        List<Post> item = postService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<Post>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Post>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getOne(@PathVariable("id") int id) {
        Post item = postService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public Post create(@RequestBody Post item) {
        return postService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> update(@PathVariable("id") int id, @RequestBody Post item) {
        Post existing = postService.findById(id);
        postService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Post item = postService.findById(id);
        postService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
