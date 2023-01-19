package com.project.splitwise.repository;

import com.project.splitwise.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ExpenseDao extends JpaRepository<Expense, Integer> {
}
