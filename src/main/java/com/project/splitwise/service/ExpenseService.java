package com.project.splitwise.service;

import com.project.splitwise.model.Balance;
import com.project.splitwise.model.Expense;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.ExpenseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ExpenseService {
    public static ExpenseDao expenseDao;
    private static BalanceDao balanceDao;

    @Autowired
    public ExpenseService(ExpenseDao expenseDao,
                          BalanceDao balanceDao) {
        ExpenseService.expenseDao = expenseDao;
        this.balanceDao = balanceDao;
    }

    //expense log bhi banana hai
    public void addExpense (Expense expense) {

        int membersCount = expense.getExpensePartners().size();
        boolean isExists  = false ;
        double splitAmount = 0;
        int index = 0;

        List<Balance> groupBalances = new ArrayList<>() ;
        for(Balance balance : balanceDao.findAll()){
            if(balance.getGroupId().equals(expense.getGroupId()))groupBalances.add(balance) ;
        }

        if(expense.getSplitPercentage().size() == 0){
           double splitPercentage = 100d/membersCount;
           List<Double> split = new ArrayList<Double>(Collections.nCopies(membersCount, splitPercentage));
           expense.setSplitPercentage(split);
        }

        if(groupBalances.size() == 0)
        {
            for(Integer groupMem : expense.getExpensePartners()){
                if(!groupMem.equals(expense.getUserId())){
                    splitAmount = (expense.getAmount() * expense.getSplitPercentage().get(index))/100;
                    Balance balance = new Balance(expense.getUserId(), groupMem, splitAmount, expense.getGroupId());
                    balanceDao.save(balance);
                }
                index++;
            }
        }
        else {
            index = 0;
            for(Integer groupMem : expense.getExpensePartners()) {
                isExists = false;
                for (Balance balance : groupBalances) {
                    splitAmount = (expense.getAmount()*(expense.getSplitPercentage().get(index)))/100;
                    if (balance.getDonorId().equals(expense.getUserId()) && balance.getReceiverId().equals(groupMem)) {
                        balance.setBalance(balance.getBalance() + splitAmount);
                        balanceDao.save(balance) ;
                        isExists = true ;
                        break ;
                    }
                }
                if(!isExists) {
                    splitAmount = (expense.getAmount() * expense.getSplitPercentage().get(index))/100;
                    Balance balance = new Balance(expense.getUserId(), groupMem, splitAmount, expense.getGroupId());
                    balanceDao.save(balance);
                }
                index++ ;
            }
        }
        expenseDao.save(expense);
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
