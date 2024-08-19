package com.codedrop.service;

import com.codedrop.model.CommentSource;

import java.util.List;

public interface CommentSourceService {

    List<CommentSource> findAll();

    CommentSource findById(Integer id);

    CommentSource create(CommentSource commentSource);

    CommentSource update(CommentSource commentSource);

    void delete(CommentSource commentSource);
}
