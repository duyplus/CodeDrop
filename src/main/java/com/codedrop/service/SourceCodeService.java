package com.codedrop.service;

import com.codedrop.model.SourceCode;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface SourceCodeService {

    List<SourceCode> findAll();

    Page<SourceCode> findPaginate(int page, int size);

    Page<SourceCode> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    SourceCode findById(Integer id);

    SourceCode create(SourceCode sourceCode);

    SourceCode update(SourceCode sourceCode);

    void delete(SourceCode sourceCode);
}
