package com.codedrop.service;

import com.codedrop.model.SourceCode;

import java.util.List;

public interface SourceCodeService {

    List<SourceCode> findAll();

    SourceCode findById(Integer id);

    SourceCode create(SourceCode sourceCode);

    SourceCode update(SourceCode sourceCode);

    void delete(SourceCode sourceCode);
}
