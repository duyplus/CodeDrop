package com.codedrop.service.impl;

import com.codedrop.model.CommentSource;
import com.codedrop.repository.CommentSourceRepository;
import com.codedrop.service.CommentSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentSourceServiceImpl implements CommentSourceService {

    @Autowired
    private CommentSourceRepository commentSourceRepository;

    @Override
    public List<CommentSource> findAll() {
        return commentSourceRepository.findAll();
    }

    @Override
    public CommentSource findById(Integer id) {
        return commentSourceRepository.findById(id).orElseThrow(() -> new RuntimeException("CommentSource not found with id " + id));
    }

    @Override
    public CommentSource create(CommentSource commentSource) {
        return commentSourceRepository.save(commentSource);
    }

    @Override
    public CommentSource update(CommentSource commentSource) {
        return commentSourceRepository.save(commentSource);
    }

    @Override
    public void delete(CommentSource commentSource) {
        commentSourceRepository.delete(commentSource);
    }
}
