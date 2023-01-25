package com.project.splitwise.service;

import com.project.splitwise.dto.ExpenseDTO;
import com.project.splitwise.model.Balance;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.ExpenseDao;
import com.project.splitwise.repository.UserDao;
import com.project.splitwise.responseModel.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    public static ExpenseDao expenseDao;
    private static BalanceDao balanceDao;
    private final UserDao userDao;

    @Autowired
    public ExpenseService(ExpenseDao expenseDao,
                          BalanceDao balanceDao,
                          UserDao userDao) {
        ExpenseService.expenseDao = expenseDao;
        this.balanceDao = balanceDao;
        this.userDao = userDao;
    }

    //expense log bhi banana hai
    public Response addExpense (Expense expense) {

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
                    Balance balance = new Balance(expense.getUserId(), groupMem, splitAmount, expense.getGroupId() , expense.getGroupName());
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
                    Balance balance = new Balance(expense.getUserId(), groupMem, splitAmount, expense.getGroupId(),expense.getGroupName());
                    balanceDao.save(balance);
                }
                index++ ;
            }
        }
        expenseDao.save(expense);
        return new Response("Expense Added" , 200);
    }

    public ExpenseDTO convertEntityToDto(Expense expense){
        ExpenseDTO expenseDTO = new ExpenseDTO() ;
        String paidBy = "" ;
        for(User user: userDao.findAll()){
         if(expense.getUserId().equals(user.getUserId()))paidBy=user.getUserName();
        }
        expenseDTO.setDescription(expense.getDescription());
        expenseDTO.setGroupName(expense.getGroupName());
        expenseDTO.setPaidBy(paidBy);
        expenseDTO.setAmountPaid(expense.getAmount());
        return expenseDTO ;
    }

        public List<ExpenseDTO> getExpenses(){
        return expenseDao.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<ExpenseDTO> getExpenseOfUser(Integer userId){
        List<ExpenseDTO> userExpenses = new ArrayList<>() ;
        for(Expense x:expenseDao.findAll()){
            if(x.getUserId().equals(userId))userExpenses.add(convertEntityToDto(x)) ;
        }
        return userExpenses ;
    }
}
