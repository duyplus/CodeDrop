package com.codedrop.service;

import com.codedrop.model.Category;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    List<Category> findAll();

    Page<Category> findPaginate(int page, int size);

    Page<Category> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    Category findById(Integer id);

    Category create(Category category);

    Category update(Category category);

    void delete(Category category);
}
