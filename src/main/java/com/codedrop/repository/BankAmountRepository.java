package com.codedrop.repository;

import com.codedrop.model.BankAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAmountRepository extends JpaRepository<BankAmount, Integer>, JpaSpecificationExecutor<BankAmount> {
}