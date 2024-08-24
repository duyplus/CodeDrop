package com.codedrop.service.impl;

import com.codedrop.common.CustomSpecification;
import com.codedrop.model.BankAmount;
import com.codedrop.repository.BankAmountRepository;
import com.codedrop.service.BankAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BankAmountServiceImpl implements BankAmountService {

    @Autowired
    private BankAmountRepository bankAmountRepository;

    @Override
    public List<BankAmount> findAll() {
        return bankAmountRepository.findAll();
    }

    public Page<BankAmount> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bankAmountRepository.findAll(pageable);
    }

    public Page<BankAmount> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<BankAmount> specification = CustomSpecification.createSpecification(conditions);
        return bankAmountRepository.findAll(specification, pageable);
    }

    @Override
    public BankAmount findById(Integer id) {
        return bankAmountRepository.findById(id).orElseThrow(() -> new RuntimeException("BankAmount not found with id " + id));
    }

    @Override
    public BankAmount create(BankAmount bankAmount) {
        return bankAmountRepository.save(bankAmount);
    }

    @Override
    public BankAmount update(BankAmount bankAmount) {
        return bankAmountRepository.save(bankAmount);
    }

    @Override
    public void delete(BankAmount bankAmount) {
        bankAmountRepository.delete(bankAmount);
    }
}
