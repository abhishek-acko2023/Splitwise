package com.project.splitwise.service;

import com.project.splitwise.dto.ExpenseDTO;
import com.project.splitwise.dto.GroupDetailDTO;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.Groups;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.ExpenseDao;
import com.project.splitwise.repository.GroupDao;
import com.project.splitwise.repository.UserDao;
import com.project.splitwise.responseModel.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private static GroupDao groupDao ;
    private static ExpenseDao expenseDao;
    private final UserDao userDao;

    @Autowired
    private ModelMapper modelMapper ;
    @Autowired
    private ExpenseService expenseService ;

    @Autowired
    public GroupService(GroupDao groupDao, ExpenseDao expenseDao,
                        UserDao userDao) {
        this.groupDao = groupDao;
        this.expenseDao = expenseDao;
        this.userDao = userDao;
    }
    public Response createGroup(Groups groups){
        groupDao.save(groups);
        return new Response("Group Added" , 200);
    }

    public GroupDetailDTO getGroup(Integer groupId) {
        GroupDetailDTO groupDetailDTO = new GroupDetailDTO() ;
        for(Groups group: groupDao.findAll()){
            if(group.getGroupId().equals(groupId)){
                groupDetailDTO = convertEntityToDto(group);
                break ;
            }
        }
        return groupDetailDTO ;
    }

    public GroupDetailDTO convertEntityToDto(Groups groups){
        List<String> groupMembers = new ArrayList<>() ;
        GroupDetailDTO groupDetailDTO = new GroupDetailDTO();
        String createdBy = "" ;
        for(Integer groupMem : groups.getGroupMembers()){
          for(User user : userDao.findAll()){
              if(user.getUserId().equals(groupMem))groupMembers.add(user.getUserName());
              if(user.getUserId().equals(groups.getGroupCreatedBy()))createdBy = user.getUserName();
          }
        }
        groupDetailDTO.setGroupMembers(groupMembers);
        groupDetailDTO.setCreatedBy(createdBy);
        groupDetailDTO.setGroupName(groups.getGroupName());
        return groupDetailDTO ;
    }
    public List<GroupDetailDTO> getGroups(){
        return groupDao.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<ExpenseDTO> getAllGroupExpenses(Integer groupId) {
        List<ExpenseDTO> allGroupExpenses = new ArrayList<>();
        for(Expense expense : expenseDao.findAll()){
            if(expense.getGroupId().equals(groupId)){
                allGroupExpenses.add(expenseService.convertEntityToDto(expense));
            }
        }
        return allGroupExpenses;
    }
    public List<ExpenseDTO> getUserGroupExpenses(Integer groupId, Integer userId) {
        List<ExpenseDTO> userGroupExpenses = new ArrayList<>();
        for(Expense expense : expenseDao.findAll()){
            if(expense.getGroupId().equals(groupId) && expense.getUserId().equals(userId)){
                userGroupExpenses.add(expenseService.convertEntityToDto(expense));
            }
        }
        return userGroupExpenses;
    }
}
