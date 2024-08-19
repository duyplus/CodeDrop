package com.codedrop.service;

import com.codedrop.model.BankAccount;

import java.util.List;

public interface BankAccountService {

    List<BankAccount> findAll();

    BankAccount findById(String id);

    BankAccount create(BankAccount bankAccount);

    BankAccount update(BankAccount bankAccount);

    void delete(BankAccount bankAccount);
}
