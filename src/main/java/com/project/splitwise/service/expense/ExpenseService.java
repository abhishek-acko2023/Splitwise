package com.project.splitwise.service.expense;

import com.project.splitwise.dto.ExpenseDTO;
import com.project.splitwise.interfaces.SplitService;
import com.project.splitwise.model.Balance;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.BalanceDao;
import com.project.splitwise.repository.ExpenseDao;
import com.project.splitwise.repository.UserDao;
import com.project.splitwise.responseModel.Response;
import com.project.splitwise.service.balance.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Configurable
public class ExpenseService {

    @Autowired
    public ExpenseDao expenseDao;
    @Autowired
    private BalanceDao balanceDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    EqualSplit equalSplit;


    public Response addExpense (Expense expense , String expenseType) {
        SplitService splitService = null;
//        System.out.println(expenseType + " " + SplitService.Split.EQUAL.name());
        if(expenseType.equals(SplitService.Split.EQUAL.name())){
            splitService = new EqualSplit();
        }else if(expenseType.equals(SplitService.Split.CUSTOM.name())){
            splitService = new CustomSplit();
        }else if(expenseType.equals(SplitService.Split.PERCENTAGE.name())){
            splitService = new PercentageSplit();
        }


        try{
            splitService.splitExpense(expense);
            expenseDao.save(expense);
            return new Response("Expense Added" , 200);
        }catch (Exception exception){
            System.out.println("Error");
            System.out.println(exception.toString());
            return new Response("Failed" , 501);
        }
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
