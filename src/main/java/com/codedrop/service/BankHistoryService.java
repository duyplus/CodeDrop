package com.codedrop.service;

import com.codedrop.model.BankHistory;

import java.util.List;

public interface BankHistoryService {

    List<BankHistory> findAll();

    BankHistory findById(Integer id);

    BankHistory create(BankHistory bankHistory);

    BankHistory update(BankHistory bankHistory);

    void delete(BankHistory bankHistory);
}
