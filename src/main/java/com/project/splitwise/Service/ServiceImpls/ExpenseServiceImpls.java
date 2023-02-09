package com.project.splitwise.Service.ServiceImpls;

import com.project.splitwise.Dao.ExpenseDao;
import com.project.splitwise.Dao.GroupDao;
import com.project.splitwise.Dao.UserDao;
import com.project.splitwise.Dto.Request.Expense;
import com.project.splitwise.Dto.Request.User;
import com.project.splitwise.Dto.Response.Expense.ExpenseDTO;
import com.project.splitwise.Dto.Response.Expense.ExpenseGroupDTO;
import com.project.splitwise.Dto.Response.Expense.UserExpenseDTO;
import com.project.splitwise.Enums.Split;
import com.project.splitwise.Repository.ExpenseRepo;
import com.project.splitwise.Service.ServiceInterface.ExpenseService;
import com.project.splitwise.Service.ServiceImpls.Split.CustomSplitService;
import com.project.splitwise.Service.ServiceImpls.Split.EqualSplitService;
import com.project.splitwise.Service.ServiceImpls.Split.PercentageSplitService;
import com.project.splitwise.Service.ServiceInterface.GroupService;
import com.project.splitwise.Service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseServiceImpls implements ExpenseService {
    public static ExpenseRepo expenseRepo;
    public static UserService userService;
    public static GroupService groupService;
    public static CustomSplitService customSplitService;
    public static EqualSplitService equalSplitService;
    public static PercentageSplitService percentageSplitService;
    @Autowired
    public ExpenseServiceImpls(ExpenseRepo expenseRepo,
                               UserService userService,
                               GroupService groupService,
                               CustomSplitService customSplitService,
                               EqualSplitService equalSplitService,
                               PercentageSplitService percentageSplitService) {
        ExpenseServiceImpls.expenseRepo = expenseRepo;
        ExpenseServiceImpls.userService = userService;
        ExpenseServiceImpls.groupService = groupService;
        ExpenseServiceImpls.customSplitService = customSplitService;
        ExpenseServiceImpls.equalSplitService = equalSplitService;
        ExpenseServiceImpls.percentageSplitService = percentageSplitService;
    }

    public int addExpense (Expense expense) {

        // throw error if only one property provided except EQUAl
        if((expense.getSplit() != null && expense.getSplitType() == null) ||
                (expense.getSplit() == null && expense.getSplitType() != null) ){
            if(expense.getSplitType() != null && expense.getSplit() == null){
                if(!expense.getSplitType().equals(Split.EQUAL.name())) return -1;
            }else return -1;
        }

        if(expense.getExpensePartners().size() < 2){
            return -1;
        }
        // split division not provided
        if(expense.getSplitType() == null && expense.getSplit() == null){
            expense.setSplitType("EQUAL");
        }

        // split expense with given type
        if(expense.getSplitType().equals(Split.EQUAL.name())){
            return equalSplitService.split(expense);
        }else if(expense.getSplitType().equals(Split.PERCENTAGE.name())){
            return percentageSplitService.split(expense);
        }else if(expense.getSplitType().equals(Split.CUSTOM.name())){
            customSplitService.split(expense);
        }
        return 1;
    }

    List<User> getUserList(List<Integer> userIds){
        List<User> users = new ArrayList<>();
        for(Integer userId : userIds){
            UserDao userDao = userService.getUser(userId);
            users.add(new User(userDao.getUserName(),userDao.getUserEmail()));
        }
        return users;
    }
    List<UserExpenseDTO> getSplit(List<Integer> userIds,List<Double> split, Double amount){
        List<UserExpenseDTO> splits = new ArrayList<>();
        int index = 0;
        for(Integer userId : userIds){
            UserDao userDao = userService.getUser(userId);
            if(split == null || split.size() == 0){
                UserExpenseDTO user = new UserExpenseDTO(userDao.getUserName(),userDao.getUserEmail(),amount/userIds.size());
                splits.add(user);
            }else{
                UserExpenseDTO user = new UserExpenseDTO(userDao.getUserName(),userDao.getUserEmail(),split.get(index));
                splits.add(user);
                index++;
            }
        }
        return splits;
    }
    ExpenseDTO generateExpenseDTO(ExpenseDao expenseDao){
        List<User> expensePartners = getUserList(expenseDao.getExpensePartners());
        List<UserExpenseDTO> splits = getSplit(expenseDao.getExpensePartners(),expenseDao.getSplit(),expenseDao.getAmount());
        UserDao userDao = userService.getUser(expenseDao.getPaidBy());
        User user = new User(userDao.getUserName(),userDao.getUserEmail());
        return new ExpenseDTO(expenseDao.getDescription(), expenseDao.getAmount(), user, expensePartners,splits, expenseDao.getSplitType());
    }

    ExpenseGroupDTO generateExpenseGroupDTO(ExpenseDao expenseDao){
        List<User> expensePartners = getUserList(expenseDao.getExpensePartners());
        List<UserExpenseDTO> splits = getSplit(expenseDao.getExpensePartners(),expenseDao.getSplit(),expenseDao.getAmount());
        UserDao userDao = userService.getUser(expenseDao.getPaidBy());
        User user = new User(userDao.getUserName(),userDao.getUserEmail());
        GroupDao groupDao = groupService.groupDetails(expenseDao.getGroupId());
        return new ExpenseGroupDTO(expenseDao.getDescription(), expenseDao.getAmount(), user, expensePartners,splits, expenseDao.getSplitType(),groupDao.getGroupName(), groupDao.getGroupDescription());
    }

    public List<Object> getExpenses(){
        List<ExpenseDao> allExpenses = expenseRepo.findAll();
        List<Object> expenses = new ArrayList<>();
        for(ExpenseDao expense : allExpenses){
            if(expense.getGroupId() == null) expenses.add(generateExpenseDTO(expense));
            else expenses.add(generateExpenseGroupDTO(expense));
        }
        return expenses;
    }

    public List<Object> getUserExpense(Integer userId){
        Optional<List<ExpenseDao>> userExpenses = expenseRepo.findByPaidBy(userId);
        if(userExpenses.isPresent()){
            List<ExpenseDao> userExpenseDao = userExpenses.get();
            List<Object> expenses = new ArrayList<>();
            for(ExpenseDao expense : userExpenseDao){
                if(expense.getGroupId() == null) expenses.add(generateExpenseDTO(expense));
                else expenses.add(generateExpenseGroupDTO(expense));
            }
            return expenses;
        }
        return new ArrayList<>();
    }

    public List<Object> getGroupExpense(Integer groupId){
        Optional<List<ExpenseDao>> groupExpenses = expenseRepo.findByGroupId(groupId);
        if(groupExpenses.isPresent()){
            List<ExpenseDao> groupExpenseDao = groupExpenses.get();
            List<Object> expenses = new ArrayList<>();
            for(ExpenseDao expense : groupExpenseDao){
               expenses.add(generateExpenseGroupDTO(expense));
            }
            return expenses;
        }
        return new ArrayList<>();
    }
}
