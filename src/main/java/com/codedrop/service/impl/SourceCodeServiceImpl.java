package com.codedrop.service.impl;

import com.codedrop.model.SourceCode;
import com.codedrop.repository.SourceCodeRepository;
import com.codedrop.service.SourceCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceCodeServiceImpl implements SourceCodeService {

    @Autowired
    SourceCodeRepository sourceCodeRepository;

    @Override
    public List<SourceCode> findAll() {
        return sourceCodeRepository.findAll();
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
