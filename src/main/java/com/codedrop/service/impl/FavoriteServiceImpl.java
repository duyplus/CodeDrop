package com.codedrop.service.impl;

import com.codedrop.model.Favorite;
import com.codedrop.repository.FavoriteRepository;
import com.codedrop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite findById(Integer id) {
        return favoriteRepository.findById(id).orElseThrow(() -> new RuntimeException("Favorite not found with id " + id));
    }

    @Override
    public Favorite create(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public Favorite update(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Override
    public void delete(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }
}
