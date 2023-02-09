package com.project.splitwise.Controller;

import com.project.splitwise.Dao.ExpenseDao;
import com.project.splitwise.Dto.Request.Expense;
import com.project.splitwise.Service.ServiceImpls.ExpenseServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/splitwise/expense")
public class ExpenseController {
  private final ExpenseServiceImpls expenseServiceImpls;
   @Autowired
    public ExpenseController(ExpenseServiceImpls expenseServiceImpls) {
        this.expenseServiceImpls = expenseServiceImpls;
    }

    @PostMapping("/add")
    public int addExpense (@RequestBody Expense expense){
       return expenseServiceImpls.addExpense(expense);
    }

    @GetMapping("/get")
    public List<Object> getExpenses(){
       return expenseServiceImpls.getExpenses();
    }

    @GetMapping("/user/{userId}")
    public List<Object> getUserExpense(@PathVariable("userId") Integer userId){
       return expenseServiceImpls.getUserExpense(userId);
    }
    @GetMapping("/group/{groupId}")
    public List<Object> getGroupExpense(@PathVariable("groupId") Integer groupId){
        return expenseServiceImpls.getGroupExpense(groupId);
    }
}
