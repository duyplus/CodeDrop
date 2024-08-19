package com.codedrop.service.impl;

import com.codedrop.model.BankHistory;
import com.codedrop.repository.BankHistoryRepository;
import com.codedrop.service.BankHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankHistoryServiceImpl implements BankHistoryService {

    @Autowired
    private BankHistoryRepository bankHistoryRepository;

    @Override
    public List<BankHistory> findAll() {
        return bankHistoryRepository.findAll();
    }

    @Override
    public BankHistory findById(Integer id) {
        return bankHistoryRepository.findById(id).orElseThrow(() -> new RuntimeException("BankHistory not found with id " + id));
    }

    @Override
    public BankHistory create(BankHistory bankHistory) {
        return bankHistoryRepository.save(bankHistory);
    }

    @Override
    public BankHistory update(BankHistory bankHistory) {
        return bankHistoryRepository.save(bankHistory);
    }

    @Override
    public void delete(BankHistory bankHistory) {
        bankHistoryRepository.delete(bankHistory);
    }
}
