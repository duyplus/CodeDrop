package com.codedrop.service.impl;

import com.codedrop.common.CustomSpecification;
import com.codedrop.model.Post;
import com.codedrop.repository.PostRepository;
import com.codedrop.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Page<Post> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findAll(pageable);
    }

    public Page<Post> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Post> specification = CustomSpecification.createSpecification(conditions);
        return postRepository.findAll(specification, pageable);
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }
}
