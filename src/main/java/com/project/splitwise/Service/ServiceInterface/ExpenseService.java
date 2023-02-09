package com.project.splitwise.Service.ServiceInterface;

import com.project.splitwise.Dto.Request.Expense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExpenseService {
     int addExpense (Expense expense);
     List<Object> getExpenses();
     List<Object> getUserExpense(Integer userId);
     List<Object> getGroupExpense(Integer groupId);
}
