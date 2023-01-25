package com.project.splitwise.service;

import com.project.splitwise.model.Expense;
import com.project.splitwise.model.Groups;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.ExpenseDao;
import com.project.splitwise.repository.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService {
    private static GroupDao groupDao ;
    private static ExpenseDao expenseDao;

    @Autowired
    public GroupService(GroupDao groupDao, ExpenseDao expenseDao) {
        this.groupDao = groupDao;
        this.expenseDao = expenseDao;
    }
    public void createGroup(Groups groups){
        groupDao.save(groups);
    }

    public Optional<Groups> getGroup(Integer groupId) {
        return groupDao.findById(groupId);
    }
    public List<Groups> getGroups(){
        return groupDao.findAll() ;
    }

    public List<Expense> getAllGroupExpenses(Integer groupId) {
        List<Expense> allGroupExpenses = new ArrayList<>();
        for(Expense expense : expenseDao.findAll()){
            if(expense.getGroupId().equals(groupId)){
                allGroupExpenses.add(expense);
            }
        }
        return allGroupExpenses;
    }
    public List<Expense> getUserGroupExpenses(Integer groupId, Integer userId) {
        List<Expense> userGroupExpenses = new ArrayList<>();
        for(Expense expense : expenseDao.findAll()){
            if(expense.getGroupId().equals(groupId) && expense.getUserId().equals(userId)){
                userGroupExpenses.add(expense);
            }
        }
        return userGroupExpenses;
    }
}
