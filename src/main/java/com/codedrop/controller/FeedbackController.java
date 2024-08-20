package com.codedrop.controller;

import com.codedrop.model.Feedback;
import com.codedrop.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/feedback")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<List<Feedback>> getAll() {
        List<Feedback> item = feedbackService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<Feedback>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Feedback>>(item, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Feedback> getOne(@PathVariable("id") int id) {
        Feedback item = feedbackService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public Feedback create(@RequestBody Feedback item) {
        return feedbackService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<Feedback> update(@PathVariable("id") int id, @RequestBody Feedback item) {
        Feedback existing = feedbackService.findById(id);
        feedbackService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Feedback item = feedbackService.findById(id);
        feedbackService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
