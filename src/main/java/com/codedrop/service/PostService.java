package com.codedrop.service;

import com.codedrop.model.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post findById(Integer id);

    Post create(Post post);

    Post update(Post post);

    void delete(Post post);
}
