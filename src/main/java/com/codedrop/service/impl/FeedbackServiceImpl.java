package com.codedrop.service.impl;

import com.codedrop.common.CustomSpecification;
import com.codedrop.model.Feedback;
import com.codedrop.repository.FeedbackRepository;
import com.codedrop.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<Feedback> findAll() {
        return feedbackRepository.findAll();
    }

    public Page<Feedback> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return feedbackRepository.findAll(pageable);
    }

    public Page<Feedback> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Feedback> specification = CustomSpecification.createSpecification(conditions);
        return feedbackRepository.findAll(specification, pageable);
    }

    @Override
    public Feedback findById(Integer id) {
        return feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Favorite not found with id " + id));
    }

    @Override
    public Feedback create(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback update(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public void delete(Feedback feedback) {
        feedbackRepository.delete(feedback);
    }
}
