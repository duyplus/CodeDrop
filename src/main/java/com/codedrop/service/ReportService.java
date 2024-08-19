package com.codedrop.service;

import com.codedrop.model.Report;

import java.util.List;

public interface ReportService {

    List<Report> findAll();

    Report findById(Integer id);

    Report create(Report report);

    Report update(Report report);

    void delete(Report report);
}
