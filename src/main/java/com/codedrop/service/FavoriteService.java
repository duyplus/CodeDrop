package com.codedrop.service;

import com.codedrop.model.Favorite;

import java.util.List;

public interface FavoriteService {

    List<Favorite> findAll();

    Favorite findById(Integer id);

    Favorite create(Favorite favorite);

    Favorite update(Favorite favorite);

    void delete(Favorite favorite);
}
