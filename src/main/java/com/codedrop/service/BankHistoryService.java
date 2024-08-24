package com.codedrop.service;

import com.codedrop.model.BankHistory;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BankHistoryService {

    List<BankHistory> findAll();

    Page<BankHistory> findPaginate(int page, int size);

    Page<BankHistory> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    BankHistory findById(Integer id);

    BankHistory create(BankHistory bankHistory);

    BankHistory update(BankHistory bankHistory);

    void delete(BankHistory bankHistory);
}
