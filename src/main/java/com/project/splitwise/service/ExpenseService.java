package com.project.splitwise.service;

import com.project.splitwise.controller.BalanceController;
import com.project.splitwise.local.BalanceList;
import com.project.splitwise.local.ExpenseList;
import com.project.splitwise.local.UserList;
import com.project.splitwise.model.Balance;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.ExpenseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {
    public static ExpenseDao expenseDao;
    @Autowired
    public ExpenseService(ExpenseDao expenseDao) {
        ExpenseService.expenseDao = expenseDao;
    }

    public void addExpense (Expense expense){
        double amountToPay = 0 ;
        int cnt = 0 ;
        Expense currentExpense = expenseDao.save(expense);
        ExpenseList.expenseList.add(currentExpense) ;
        try{
            amountToPay = expense.getAmount()/UserList.usersList.size() ;
        }catch (ArithmeticException exception){
            System.out.println(exception.getMessage());
        }
        for(Balance x: BalanceList.balanceList){
            if(x.getDonorId().equals(currentExpense.getUserId())){
                //update in db
                x.setBalance(x.getBalance()+amountToPay);
                BalanceController.updateBalance(currentExpense.getUserId(),x.getReceiverId(),amountToPay);
                cnt++ ;
            }
        }
        if(cnt==0){
            for(User x: UserList.usersList){
                if(!x.getUserId().equals(currentExpense.getUserId())){
                    System.out.println(x.getUserId());
                    BalanceList.balanceList.add(new Balance(currentExpense.getUserId(),x.getUserId(),amountToPay));
                    BalanceController.createBalance(new Balance(currentExpense.getUserId(),x.getUserId(),amountToPay));
                }
            }
        }

    }
    public List<Expense> getExpenses(){
        return expenseDao.findAll();
    }

    public List<Expense> getExpenseOfUser(Integer userId){
        List<Expense> userExpenses = new ArrayList<>() ;
        for(Expense x:expenseDao.findAll()){
            if(x.getUserId().equals(userId))userExpenses.add(x) ;
        }
        return userExpenses ;
    }
}
