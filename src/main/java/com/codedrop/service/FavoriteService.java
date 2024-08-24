package com.codedrop.service;

import com.codedrop.model.Favorite;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface FavoriteService {

    List<Favorite> findAll();

    Page<Favorite> findPaginate(int page, int size);

    Page<Favorite> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    Favorite findById(Integer id);

    Favorite create(Favorite favorite);

    Favorite update(Favorite favorite);

    void delete(Favorite favorite);
}
