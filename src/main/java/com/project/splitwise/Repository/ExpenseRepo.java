package com.project.splitwise.Repository;

import com.project.splitwise.Dao.BalanceDao;
import com.project.splitwise.Dao.ExpenseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepo extends JpaRepository<ExpenseDao, Integer> {
    Optional<List<ExpenseDao>> findByPaidBy(Integer paidBy);
    Optional<List<ExpenseDao>> findByGroupId(Integer groupId);
}
