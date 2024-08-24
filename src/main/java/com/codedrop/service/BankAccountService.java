package com.codedrop.service;

import com.codedrop.model.BankAccount;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BankAccountService {

    List<BankAccount> findAll();

    Page<BankAccount> findPaginate(int page, int size);

    Page<BankAccount> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    BankAccount findById(String id);

    BankAccount create(BankAccount bankAccount);

    BankAccount update(BankAccount bankAccount);

    void delete(BankAccount bankAccount);
}
