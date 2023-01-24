package com.project.splitwise.controller;

import com.project.splitwise.Response;
import com.project.splitwise.model.Groups;
import com.project.splitwise.model.User;
import com.project.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/splitwise/group")
public class GroupController {
    private final GroupService groupService ;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create")
    public void createGroup(@RequestBody Groups group){
        groupService.createGroup(group);
    }

    @GetMapping("/getGroups")
    public List<Groups> getGroups(){
        return  groupService.getGroups() ;
    }
}
