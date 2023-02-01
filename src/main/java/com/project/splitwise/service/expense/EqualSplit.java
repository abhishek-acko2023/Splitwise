package com.project.splitwise.service.expense;

import com.project.splitwise.interfaces.SplitService;
import com.project.splitwise.model.Balance;
import com.project.splitwise.model.Expense;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.ExpenseDao;
import com.project.splitwise.service.balance.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EqualSplit implements SplitService {
//    @Autowired
//    private BalanceDao balanceDao;
//    @Autowired
//    private Transaction trans ;
    @Autowired
    ApplicationContext applicationContext ;
    @Override
    public void splitExpense(Expense expense) {

//        Transaction transaction = applicationContext.getBean(Transaction.class);
//        BalanceDao dao = applicationContext.getBean(BalanceDao.class);
        int membersCount = expense.getExpensePartners().size();
        double splitAmount = expense.getAmount()/membersCount ;

        if(expense.getGroupId() != null){
            for(Integer groupMem : expense.getExpensePartners()){
//                dao.save(transaction.isFirstBalance(expense,groupMem,splitAmount));
                applicationContext.getBean(BalanceDao.class).save(isFirstBalance(expense,groupMem,splitAmount));
            }
        }else{
//            dao.save(transaction.isFirstBalance(expense,expense.getExpensePartners().get(1),splitAmount));
            applicationContext.getBean(BalanceDao.class).save(isFirstBalance(expense,expense.getExpensePartners().get(1),splitAmount));
        }
    }

    public Balance isFirstBalance(Expense expense , int groupMem , double splitAmount){
                BalanceDao dao = applicationContext.getBean(BalanceDao.class);
        if(expense.getGroupId() != null){
            List<Balance> groupBalances = new ArrayList<>() ;
            for(Balance balance : dao.findAll()){
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
            for(Balance balance : dao.findAll()){
                if(balance.getDonorId().equals(expense.getUserId()) && balance.getReceiverId().equals(groupMem)){
                    balance.setBalance(balance.getBalance() + splitAmount);
                    return balance ;
                }
            }
            return new Balance(expense.getUserId(), groupMem, splitAmount);
        }
    }

}
