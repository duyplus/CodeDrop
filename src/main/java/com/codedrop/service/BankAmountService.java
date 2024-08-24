package com.codedrop.service;

import com.codedrop.model.BankAmount;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BankAmountService {

    List<BankAmount> findAll();

    Page<BankAmount> findPaginate(int page, int size);

    Page<BankAmount> findPaginateWithConditions(int page, int size, Map<String, String> conditions);

    BankAmount findById(Integer id);

    BankAmount create(BankAmount bankAmount);

    BankAmount update(BankAmount bankAmount);

    void delete(BankAmount bankAmount);
}
