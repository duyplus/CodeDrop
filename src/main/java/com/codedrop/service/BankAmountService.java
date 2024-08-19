package com.codedrop.service;

import com.codedrop.model.BankAmount;

import java.util.List;

public interface BankAmountService {

    List<BankAmount> findAll();

    BankAmount findById(Integer id);

    BankAmount create(BankAmount bankAmount);

    BankAmount update(BankAmount bankAmount);

    void delete(BankAmount bankAmount);
}
