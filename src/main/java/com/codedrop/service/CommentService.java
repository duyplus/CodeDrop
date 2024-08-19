package com.codedrop.service;

import com.codedrop.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment findById(Integer id);

    Comment create(Comment comment);

    Comment update(Comment comment);

    void delete(Comment comment);
}
