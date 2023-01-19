package com.project.splitwise.controller;

import com.project.splitwise.model.Expense;
import com.project.splitwise.model.User;
import com.project.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/splitwise/expense")
public class ExpenseController {
  private final ExpenseService expenseService ;
   @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/add")
    public void addExpense (@RequestBody Expense expense){
       expenseService.addExpense(expense);
    }
    @GetMapping("/get")
    public List<Expense> getExpenses(){
       return expenseService.getExpenses();
    }

    @GetMapping("/byUser/{userId}")
    public List<Expense> getExpenseOfUser(@PathVariable("userId") Integer userId){return expenseService.getExpenseOfUser(userId) ;}
}
