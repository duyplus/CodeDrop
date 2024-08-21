package com.codedrop.controller;

import com.codedrop.model.User;
import com.codedrop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder pe;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page == null || size == null) {
            List<User> users = userService.findAll();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        Page<User> usersPage = userService.findPaginate(page, size);
        return new ResponseEntity<>(usersPage, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") int id) {
        User item = userService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public User create(@RequestBody User item) {
        item.setPassword(pe.encode(item.getPassword()));
        return userService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody User item) {
        User existing = userService.findById(id);
        item.setPassword(pe.encode(item.getPassword()));
        userService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        User item = userService.findById(id);
        userService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
