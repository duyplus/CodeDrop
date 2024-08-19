package com.codedrop.service.impl;

import com.codedrop.model.Report;
import com.codedrop.repository.ReportRepository;
import com.codedrop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
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
