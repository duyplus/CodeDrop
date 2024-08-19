package com.codedrop.service;

import com.codedrop.model.Feedback;

import java.util.List;

public interface FeedbackService {

    List<Feedback> findAll();

    Feedback findById(Integer id);

    Feedback create(Feedback feedback);

    Feedback update(Feedback feedback);

    void delete(Feedback feedback);
}
