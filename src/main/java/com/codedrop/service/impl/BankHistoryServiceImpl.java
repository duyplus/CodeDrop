package com.codedrop.service.impl;

import com.codedrop.common.CustomSpecification;
import com.codedrop.model.BankHistory;
import com.codedrop.repository.BankHistoryRepository;
import com.codedrop.service.BankHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BankHistoryServiceImpl implements BankHistoryService {

    @Autowired
    private BankHistoryRepository bankHistoryRepository;

    @Override
    public List<BankHistory> findAll() {
        return bankHistoryRepository.findAll();
    }

    public Page<BankHistory> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bankHistoryRepository.findAll(pageable);
    }

    public Page<BankHistory> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<BankHistory> specification = CustomSpecification.createSpecification(conditions);
        return bankHistoryRepository.findAll(specification, pageable);
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
