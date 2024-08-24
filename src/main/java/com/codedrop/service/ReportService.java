package com.codedrop.service;

import com.codedrop.model.Report;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ReportService {

    List<Report> findAll();

    Page<Report> findPaginate(int page, int size);

    Page<Report> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    Report findById(Integer id);

    Report create(Report report);

    Report update(Report report);

    void delete(Report report);
}
