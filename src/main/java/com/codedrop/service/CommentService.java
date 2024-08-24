package com.codedrop.service;

import com.codedrop.model.Comment;
import com.codedrop.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CommentService {

    List<Comment> findAll();

    Page<Comment> findPaginate(int page, int size);

    Page<Comment> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    Comment findById(Integer id);

    Comment create(Comment comment);

    Comment update(Comment comment);

    void delete(Comment comment);
}
