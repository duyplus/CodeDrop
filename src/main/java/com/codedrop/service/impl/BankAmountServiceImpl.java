package com.codedrop.service.impl;

import com.codedrop.model.BankAmount;
import com.codedrop.repository.BankAmountRepository;
import com.codedrop.service.BankAmountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAmountServiceImpl implements BankAmountService {

    @Autowired
    private BankAmountRepository bankAmountRepository;

    @Override
    public List<BankAmount> findAll() {
        return bankAmountRepository.findAll();
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
