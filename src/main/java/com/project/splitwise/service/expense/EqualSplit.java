package com.project.splitwise.service.expense;

import com.project.splitwise.model.Balance;
import com.project.splitwise.model.Expense;
import com.project.splitwise.dao.BalanceDao;
import com.project.splitwise.service.balance.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EqualSplit implements SplitService {
    @Autowired
    private BalanceDao balanceDao;
    @Override
    public void splitExpense(Expense expense) {

        int membersCount = expense.getExpensePartners().size();
        double splitAmount = expense.getAmount()/membersCount ;

        if(expense.getGroupId() != null){
            for(Integer groupMem : expense.getExpensePartners()){
                if(groupMem!=expense.getUserId())balanceDao.save(isFirstBalance(expense,groupMem,splitAmount));
            }
        }else{
            for(Integer groupMem : expense.getExpensePartners()) {
                if(groupMem!=expense.getUserId())balanceDao.save(isFirstBalance(expense, groupMem, splitAmount));
            }
        }
    }

    public Balance isFirstBalance(Expense expense , int groupMem , double splitAmount){
        if(expense.getGroupId() != null){
            List<Balance> groupBalances = new ArrayList<>() ;
            for(Balance balance : balanceDao.findAll()){
                if(balance.getGroupId().equals(expense.getGroupId()))groupBalances.add(balance) ;
            }
            for (Balance balance : groupBalances) {
                if (balance.getDonorId().equals(expense.getUserId()) && balance.getReceiverId().equals(groupMem)) {
                    balance.setBalance(balance.getBalance() + splitAmount);
                    return balance ;
                }
            }

            return new Balance(expense.getUserId(), groupMem, splitAmount, expense.getGroupId(),expense.getGroupName());
        }
        else{
            for(Balance balance : balanceDao.findAll()){
                if(balance.getDonorId().equals(expense.getUserId()) && balance.getReceiverId().equals(groupMem)){
                    balance.setBalance(balance.getBalance() + splitAmount);
                    return balance ;
                }
            }
            return new Balance(expense.getUserId(), groupMem, splitAmount);
        }
    }

}