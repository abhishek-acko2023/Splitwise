package com.project.splitwise.service;

import com.project.splitwise.controller.BalanceController;
import com.project.splitwise.local.BalanceList;
import com.project.splitwise.local.ExpenseList;
import com.project.splitwise.local.UserList;
import com.project.splitwise.model.Balance;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.ExpenseDao;
import com.project.splitwise.repository.UserDao;
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
    public void addExpense (Expense expense){
        int membersCount = expense.getExpensePartners().size();
        boolean isExists  = false ;
        if(expense.getSplitPercentage().size() == 0){
           double splitPercentage = 100d/membersCount;
           List<Double> split = new ArrayList<Double>(Collections.nCopies(membersCount, splitPercentage));
           expense.setSplitPercentage(split);
        }
        double splitAmount = 0;
//        List<Balance> groupBalances = balanceDao.findAllById(Collections.singletonList(expense.getGroupId()));
//        System.out.println(groupBalances);
        List<Balance> groupBalances = new ArrayList<>() ;
        for(Balance balance : balanceDao.findAll()){
            if(balance.getGroupId().equals(expense.getGroupId()))groupBalances.add(balance) ;
        }
        System.out.println(groupBalances.size());
        int index = 0;
        if(groupBalances.size() == 0){ // no balances present create new
            for(Integer groupMem : expense.getExpensePartners()){
                if(!groupMem.equals(expense.getUserId())){
                    splitAmount = (expense.getAmount() * expense.getSplitPercentage().get(index))/100;
                    System.out.println(splitAmount + " " + expense.getAmount() + " " + expense.getSplitPercentage().get(index) + 1);
                    Balance balance = new Balance(expense.getUserId(), groupMem, splitAmount, expense.getGroupId());
                    balanceDao.save(balance);
                }
                index++;
            }
        }else{ // balances exist just update them
            for(Integer groupMem : expense.getExpensePartners()) {
                isExists = false ;
                index = 0 ;
                for (Balance balance : groupBalances) {
                    splitAmount = (expense.getAmount()*(expense.getSplitPercentage().get(index)))/100 ;
                    if (expense.getUserId().equals(balance.getDonorId()) && balance.getReceiverId().equals(groupMem)){
                        System.out.println(splitAmount + " " + expense.getAmount() + " " + expense.getSplitPercentage().get(index) + 2 + groupMem);
                        balance.setBalance(balance.getBalance()+splitAmount);
                        balanceDao.save(balance) ;
                        isExists = true ;
                        break ;
                    }
                }
                if(!isExists){
                        splitAmount = (expense.getAmount()*expense.getSplitPercentage().get(index))/100 ;
                    System.out.println(splitAmount + " " + expense.getAmount() + " " + expense.getSplitPercentage().get(index) + 3 + groupMem);
                        Balance balance = new Balance(expense.getUserId(), groupMem, splitAmount, expense.getGroupId());
                        balanceDao.save(balance);
                }
                index++ ;
            }
        }
//senger code
//        for(Balance balance : groupBalances){
//            for(Integer groupMember : expense.getExpensePartners()){
//                if(balance.getDonorId().equals(expense.getUserId()) && balance.getReceiverId().equals(expense.getExpensePartners().indexOf(groupMember))){
//                    // update balance
//
//                }
//            }
//        }
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
