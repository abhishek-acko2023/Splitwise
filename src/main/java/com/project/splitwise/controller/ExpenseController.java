package com.project.splitwise.controller;

import com.project.splitwise.dto.ExpenseDTO;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.User;
import com.project.splitwise.responseModel.Response;
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
    public Response addExpense (@RequestBody Expense expense){
       return expenseService.addExpense(expense);
    }
    @GetMapping("/")
    public List<ExpenseDTO> getExpenses(){
       return expenseService.getExpenses();
    }

    @GetMapping("/{userId}")
    public List<ExpenseDTO> getExpenseOfUser(@PathVariable("userId") Integer userId){return expenseService.getExpenseOfUser(userId) ;}
}
