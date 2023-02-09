package com.project.splitwise.Service.ServiceImpls.Split;

import com.project.splitwise.Dao.BalanceDao;
import com.project.splitwise.Dao.ExpenseDao;
import com.project.splitwise.Dao.GroupDao;
import com.project.splitwise.Dto.Request.Expense;
import com.project.splitwise.Repository.BalanceRepo;
import com.project.splitwise.Repository.ExpenseRepo;
import com.project.splitwise.Repository.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EqualSplitService {

    public static BalanceRepo balanceRepo;
    public static GroupRepo groupRepo;
    public static ExpenseRepo expenseRepo;

    @Autowired
    EqualSplitService(BalanceRepo balanceRepo, GroupRepo groupRepo, ExpenseRepo expenseRepo){
        EqualSplitService.balanceRepo = balanceRepo;
        EqualSplitService.groupRepo = groupRepo;
        EqualSplitService.expenseRepo = expenseRepo;
    }

    double getSplitAmount(double amount, Integer members){
        return amount/members;
    }

    List<Double> getSplits(Map<Integer,Double> splits){
        List<Double> split = new ArrayList<>();
        if(splits == null)
            return split;
        for(Integer partner : splits.keySet()){
            split.add(splits.get(partner));
        }
        return split;
    }
    public int split(Expense expense) {

        List<BalanceDao> balances = balanceRepo.findAll();
        HashMap<Integer, Boolean> updated = new HashMap<>();
        double splitAmount = getSplitAmount(expense.getAmount(),expense.getExpensePartners().size());

        // not in group
        if(expense.getGroupId() == null){
            ExpenseDao expenseDao = new
                    ExpenseDao(expense.getDescription(),
                    expense.getAmount(),
                    expense.getPaidBy(),
                    expense.getExpensePartners(),
                    expense.getSplitType(),
                    getSplits(expense.getSplit()));
            try{
                expenseRepo.save(expenseDao);
            }catch (Exception e){
                return -2;
            }

            // checking all existing balances with receiver as paidBy and payer as expense partner
            for(BalanceDao balance : balances){
                for(Integer partner : expense.getExpensePartners()){
                    if(partner.equals(expense.getPaidBy())) {
                        continue;
                    }
                    // balance already exists
                    if(balance.getReceiverId().equals(expense.getPaidBy()) && balance.getPayerId().equals(partner)){
                        balance.setBalance(balance.getBalance() + splitAmount);
                        try{
                            balanceRepo.save(balance);
                            updated.put(partner,true);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                            return -2;
                        }
                    }
                }
            }

            // generating new balances with receiver as paidBy and payer as expense partner
            for(Integer partner : expense.getExpensePartners()){
                if(updated.get(partner) == null){
                    BalanceDao balanceDao = new BalanceDao(partner, expense.getPaidBy(), splitAmount);
                    try{
                        balanceRepo.save(balanceDao);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                        return -2;
                    }
                }
            }

        }else{
            Optional<GroupDao> optionalGroupDao = groupRepo.findByGroupId(expense.getGroupId());
            if(optionalGroupDao.isPresent()){
                ExpenseDao expenseDao = new
                        ExpenseDao(expense.getDescription(),
                        expense.getAmount(),
                        expense.getPaidBy(),
                        expense.getExpensePartners(),
                        expense.getSplitType(),
                        getSplits(expense.getSplit()),
                        expense.getGroupId());
                try{
                    expenseRepo.save(expenseDao);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return -2;
                }

                // checking all existing balances with receiver as paidBy and payer as expense partner and in same group
                for(BalanceDao balance : balances){
                    for(Integer partner : expense.getExpensePartners()){
                        if(partner.equals(expense.getPaidBy())) {
                            continue;
                        }
                        // balance already exists
                        if(balance.getGroupId() != null && balance.getReceiverId().equals(expense.getPaidBy()) && balance.getPayerId().equals(partner) && balance.getGroupId().equals(expense.getGroupId())){
                            balance.setBalance(balance.getBalance() + splitAmount);
                            try{
                                balanceRepo.save(balance);
                                updated.put(partner,true);
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                                return -2;
                            }
                        }
                    }
                }

                // generating new balances with receiver as paidBy and payer as expense partner
                for(Integer partner : expense.getExpensePartners()){
                    if(updated.get(partner) == null){
                        BalanceDao balanceDao = new BalanceDao(partner, expense.getPaidBy(), splitAmount,expense.getGroupId());
                        try{
                            balanceRepo.save(balanceDao);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                            return -2;
                        }
                    }
                }
                return 1;
            }
            return -1;
        }
        return 1;
    }
}
