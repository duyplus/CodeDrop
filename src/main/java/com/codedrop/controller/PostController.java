package com.codedrop.controller;

import com.codedrop.common.CustomPage;
import com.codedrop.common.CustomQueryParsing;
import com.codedrop.model.Post;
import com.codedrop.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    private CustomQueryParsing customQueryParsing;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String query) {
        if (query == null) {
            List<Post> items = postService.findAll();
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
                Page<Post> paginatedUsers = postService.findPaginateWithConditions(page, size, conditions);
                CustomPage<Post> customPage = new CustomPage<>(paginatedUsers);
                return new ResponseEntity<>(customPage, HttpStatus.OK);
            } catch (CustomQueryParsing.InvalidQueryFormatException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
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
