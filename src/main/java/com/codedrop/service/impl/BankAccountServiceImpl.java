package com.codedrop.service.impl;

import com.codedrop.common.CustomSpecification;
import com.codedrop.model.BankAccount;
import com.codedrop.repository.BankAccountRepository;
import com.codedrop.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public List<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    public Page<BankAccount> findPaginate(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bankAccountRepository.findAll(pageable);
    }

    public Page<BankAccount> findPaginateWithConditions(int page, int size, Map<String, String> conditions) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<BankAccount> specification = CustomSpecification.createSpecification(conditions);
        return bankAccountRepository.findAll(specification, pageable);
    }

    @Override
    public BankAccount findById(String id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException("BankAccount not found with id " + id));
    }

    @Override
    public BankAccount create(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public void delete(BankAccount bankAccount) {
        bankAccountRepository.delete(bankAccount);
    }
}
