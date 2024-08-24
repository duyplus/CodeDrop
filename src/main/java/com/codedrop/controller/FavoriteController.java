package com.codedrop.controller;

import com.codedrop.common.CustomPage;
import com.codedrop.common.CustomQueryParsing;
import com.codedrop.model.Favorite;
import com.codedrop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/favorite")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @Autowired
    private CustomQueryParsing customQueryParsing;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String query) {
        if (query == null) {
            List<Favorite> items = favoriteService.findAll();
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
                Page<Favorite> paginatedUsers = favoriteService.findPaginateWithConditions(page, size, conditions);
                CustomPage<Favorite> customPage = new CustomPage<>(paginatedUsers);
                return new ResponseEntity<>(customPage, HttpStatus.OK);
            } catch (CustomQueryParsing.InvalidQueryFormatException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Favorite> getOne(@PathVariable("id") int id) {
        Favorite item = favoriteService.findById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public Favorite create(@RequestBody Favorite item) {
        return favoriteService.create(item);
    }

    @PutMapping("{id}")
    public ResponseEntity<Favorite> update(@PathVariable("id") int id, @RequestBody Favorite item) {
        Favorite existing = favoriteService.findById(id);
        favoriteService.update(item);
        return ResponseEntity.ok(existing);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id) {
        Favorite item = favoriteService.findById(id);
        favoriteService.delete(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
