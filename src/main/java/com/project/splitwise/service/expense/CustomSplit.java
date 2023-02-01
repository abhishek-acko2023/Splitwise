package com.project.splitwise.service.expense;

import com.project.splitwise.interfaces.SplitService;
import com.project.splitwise.model.Expense;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.ExpenseDao;
import com.project.splitwise.service.balance.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomSplit implements SplitService {
    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private Transaction trans ;

    @Override
    public void splitExpense(Expense expense) {
        int index = 1;
        int membersCount = expense.getExpensePartners().size();

        if(!expense.getGroupId().equals(null)){
            for(Integer groupMem : expense.getExpensePartners()){
                balanceDao.save(trans.isFirstBalance(expense,groupMem,expense.getSplits().get(index)));
                index++ ;
            }
        }else{
            balanceDao.save(trans.isFirstBalance(expense,expense.getExpensePartners().get(1),expense.getSplits().get(index)));
        }
    }
}
