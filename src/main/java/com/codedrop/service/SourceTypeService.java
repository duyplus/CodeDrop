package com.codedrop.service;

import com.codedrop.model.SourceType;

import java.util.List;

public interface SourceTypeService {

    List<SourceType> findAll();

    SourceType findById(Integer id);

    SourceType create(SourceType sourceType);

    SourceType update(SourceType sourceType);

    void delete(SourceType sourceType);
}
