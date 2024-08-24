package com.codedrop.service;

import com.codedrop.model.Feedback;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface FeedbackService {

    List<Feedback> findAll();

    Page<Feedback> findPaginate(int page, int size);

    Page<Feedback> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    Feedback findById(Integer id);

    Feedback create(Feedback feedback);

    Feedback update(Feedback feedback);

    void delete(Feedback feedback);
}
