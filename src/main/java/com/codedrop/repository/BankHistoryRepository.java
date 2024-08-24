package com.codedrop.repository;

import com.codedrop.model.BankHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BankHistoryRepository extends JpaRepository<BankHistory, Integer>, JpaSpecificationExecutor<BankHistory> {
}