package com.project.splitwise.controller;

import com.project.splitwise.dto.ExpenseDTO;
import com.project.splitwise.model.Expense;
import com.project.splitwise.responseModel.Response;
import com.project.splitwise.service.expense.ExpenseService;
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


    @PostMapping("/add/{expenseType}")
    public Response addExpense (@RequestBody Expense expense , @PathVariable String expenseType){
       return expenseService.addExpense(expense , expenseType);
    }
    @GetMapping("/")
    public List<ExpenseDTO> getExpenses(){
       return expenseService.getExpenses();
    }

    @GetMapping("/{userId}")
    public List<ExpenseDTO> getExpenseOfUser(@PathVariable("userId") Integer userId){return expenseService.getExpenseOfUser(userId) ;}
}
