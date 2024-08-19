package com.codedrop.service.impl;

import com.codedrop.model.SourceType;
import com.codedrop.repository.SourceTypeRepository;
import com.codedrop.service.SourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourceTypeServiceImpl implements SourceTypeService {

    @Autowired
    SourceTypeRepository sourceTypeRepository;

    @Override
    public List<SourceType> findAll() {
        return sourceTypeRepository.findAll();
    }

    @Override
    public SourceType findById(Integer id) {
        return sourceTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("SourceType not found with id " + id));
    }

    @Override
    public SourceType create(SourceType sourceType) {
        return sourceTypeRepository.save(sourceType);
    }

    @Override
    public SourceType update(SourceType sourceType) {
        return sourceTypeRepository.save(sourceType);
    }

    @Override
    public void delete(SourceType sourceType) {
        sourceTypeRepository.delete(sourceType);
    }
}
