package com.codedrop.controller;

import com.codedrop.common.CustomPage;
import com.codedrop.common.CustomQueryParsing;
import com.codedrop.model.Comment;
import com.codedrop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    private CustomQueryParsing customQueryParsing;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String query) {
        if (query == null) {
            List<Comment> items = commentService.findAll();
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
                Page<Comment> paginatedUsers = commentService.findPaginateWithConditions(page, size, conditions);
                CustomPage<Comment> customPage = new CustomPage<>(paginatedUsers);
                return new ResponseEntity<>(customPage, HttpStatus.OK);
            } catch (CustomQueryParsing.InvalidQueryFormatException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
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
