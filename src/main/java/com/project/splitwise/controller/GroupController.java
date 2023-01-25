package com.project.splitwise.controller;

import com.project.splitwise.dto.ExpenseDTO;
import com.project.splitwise.dto.GroupDetailDTO;
import com.project.splitwise.model.Expense;
import com.project.splitwise.model.Groups;
import com.project.splitwise.responseModel.Response;
import com.project.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/splitwise/group")
public class GroupController {
    private final GroupService groupService ;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @PostMapping("/create")
    public Response createGroup(@RequestBody Groups group){
        return groupService.createGroup(group);
    }
    @GetMapping("/{groupId}")
    public GroupDetailDTO getGroup(@PathVariable ("groupId") Integer groupId){
        return groupService.getGroup(groupId);
    }
    @GetMapping("/")
    public List<GroupDetailDTO> getGroups(){
        return  groupService.getGroups() ;
    }
    @GetMapping("/expense/{groupId}")
    public List<ExpenseDTO> getAllGroupExpenses(@PathVariable ("groupId") Integer groupId){
        return groupService.getAllGroupExpenses(groupId);
    }

    @GetMapping("/expense/{groupId}/{userId}")
    public List<ExpenseDTO> getUserGroupExpenses(@PathVariable("groupId") Integer groupId,
                                                 @PathVariable("userId") Integer userId){
        return groupService.getUserGroupExpenses(groupId,userId);
    }

}
