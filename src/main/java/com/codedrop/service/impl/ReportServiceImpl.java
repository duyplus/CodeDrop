package com.codedrop.service.impl;

import com.codedrop.common.CustomSpecification;
import com.codedrop.model.Report;
import com.codedrop.repository.ReportRepository;
import com.codedrop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public Page<Report> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reportRepository.findAll(pageable);
    }

    public Page<Report> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<Report> specification = CustomSpecification.createSpecification(conditions);
        return reportRepository.findAll(specification, pageable);
    }

    @Override
    public Report findById(Integer id) {
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Report not found with id " + id));
    }

    @Override
    public Report create(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Report update(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public void delete(Report report) {
        reportRepository.delete(report);
    }
}
