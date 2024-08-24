package com.codedrop.service.impl;

import com.codedrop.common.CustomSpecification;
import com.codedrop.model.SourceCode;
import com.codedrop.repository.SourceCodeRepository;
import com.codedrop.service.SourceCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SourceCodeServiceImpl implements SourceCodeService {

    @Autowired
    SourceCodeRepository sourceCodeRepository;

    @Override
    public List<SourceCode> findAll() {
        return sourceCodeRepository.findAll();
    }

    public Page<SourceCode> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return sourceCodeRepository.findAll(pageable);
    }

    public Page<SourceCode> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<SourceCode> specification = CustomSpecification.createSpecification(conditions);
        return sourceCodeRepository.findAll(specification, pageable);
    }

    @Override
    public SourceCode findById(Integer id) {
        return sourceCodeRepository.findById(id).orElseThrow(() -> new RuntimeException("SourceCode not found with id " + id));
    }

    @Override
    public SourceCode create(SourceCode sourceCode) {
        return sourceCodeRepository.save(sourceCode);
    }

    @Override
    public SourceCode update(SourceCode sourceCode) {
        return sourceCodeRepository.save(sourceCode);
    }

    @Override
    public void delete(SourceCode sourceCode) {
        sourceCodeRepository.delete(sourceCode);
    }
}
