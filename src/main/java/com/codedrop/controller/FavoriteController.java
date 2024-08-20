package com.codedrop.controller;

import com.codedrop.model.Favorite;
import com.codedrop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/favorite")
public class FavoriteController {

    @Autowired
    FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<Favorite>> getAll() {
        List<Favorite> item = favoriteService.findAll();
        if (item.isEmpty()) {
            return new ResponseEntity<List<Favorite>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Favorite>>(item, HttpStatus.OK);
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
