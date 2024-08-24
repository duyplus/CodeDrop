package com.codedrop.controller;

import com.codedrop.dto.CustomPage;
import com.codedrop.model.User;
import com.codedrop.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PasswordEncoder pe;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String query) {
        Map<String, String> conditions = new HashMap<>();
        Integer page = null;
        Integer size = null;
        if (query == null) {
            List<User> item = userService.findAll();
            if (item.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(item, HttpStatus.OK);
        } else {
            try {
                JsonNode queryNode = objectMapper.readTree(query);
                // Xử lý condition
                JsonNode conditionNode = queryNode.get("condition");
                if (conditionNode != null && conditionNode.isObject()) {
                    conditionNode.fields().forEachRemaining(entry -> {
                        conditions.put(entry.getKey(), entry.getValue().asText());
                    });
                }
                // Xử lý pagination
                JsonNode paginationNode = queryNode.get("pagination");
                if (paginationNode != null) {
                    page = paginationNode.has("page") ? paginationNode.get("page").asInt() : 0;
                    size = paginationNode.has("pageSize") ? paginationNode.get("pageSize").asInt() : 10;
                }
            } catch (IOException e) {
                return new ResponseEntity<>("Invalid query format", HttpStatus.BAD_REQUEST);
            }
        }
        // Gọi service để lấy dữ liệu dựa trên các điều kiện và thông tin phân trang
        Page<User> paginatedUsers = userService.findPaginateWithConditions(page, size, conditions);
        CustomPage<User> customPage = new CustomPage<>(paginatedUsers);
        return new ResponseEntity<>(customPage, HttpStatus.OK);
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
