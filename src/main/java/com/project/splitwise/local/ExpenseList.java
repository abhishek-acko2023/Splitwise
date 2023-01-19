package com.project.splitwise.local;

import com.project.splitwise.model.Expense;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.ExpenseDao;
import com.project.splitwise.repository.UserDao;
import com.project.splitwise.service.ExpenseService;
import com.project.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ExpenseList {
    public static List<Expense> expenseList = new ArrayList<>();

    private static ExpenseDao expenseDao;
    private static ExpenseList instance ;
    @Autowired
    private ExpenseList(ExpenseDao expenseDao) {
        ExpenseList.expenseDao = expenseDao ;
         expenseList = expenseDao.findAll();
    }

    public static ExpenseList getInstance(){
        if(instance==null){
            instance = new ExpenseList(ExpenseService.expenseDao);
        }
        return instance ;
    }

}
