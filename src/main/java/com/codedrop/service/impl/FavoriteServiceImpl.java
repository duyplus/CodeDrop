package com.codedrop.service.impl;

import com.codedrop.common.CustomSpecification;
import com.codedrop.model.Favorite;
import com.codedrop.repository.FavoriteRepository;
import com.codedrop.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> findAll() {
        return favoriteRepository.findAll();
    }

    public Page<Favorite> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return favoriteRepository.findAll(pageable);
    }

    public Page<Favorite> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Favorite> specification = CustomSpecification.createSpecification(conditions);
        return favoriteRepository.findAll(specification, pageable);
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
