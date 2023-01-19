package com.project.splitwise.repository;

import com.project.splitwise.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceDao extends JpaRepository<Balance,Integer> {
}
