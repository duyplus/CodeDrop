package com.codedrop.controller;

import com.codedrop.model.Authority;
import com.codedrop.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/authority")
public class AuthorityController {

    @Autowired
    AuthorityService authorityService;

    @GetMapping
    public ResponseEntity<List<Authority>> getAll() {
        List<Authority> authorities = authorityService.findAll();
        if (authorities.isEmpty()) {
            return new ResponseEntity<List<Authority>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Authority>>(authorities, HttpStatus.OK);
    }

    @PostMapping
    public Authority create(@RequestBody Authority authority) {
        return authorityService.create(authority);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Authority authority = authorityService.findById(id);
        authorityService.delete(authority);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
