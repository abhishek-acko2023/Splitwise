package com.project.splitwise.repository;

import com.project.splitwise.model.Balance;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BalanceDao extends JpaRepository<Balance,Integer> {
}
