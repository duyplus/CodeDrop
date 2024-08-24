package com.codedrop.service;

import com.codedrop.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface PostService {

    List<Post> findAll();

    Page<Post> findPaginate(int page, int size);

    Page<Post> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    Post findById(Integer id);

    Post create(Post post);

    Post update(Post post);

    void delete(Post post);
}
