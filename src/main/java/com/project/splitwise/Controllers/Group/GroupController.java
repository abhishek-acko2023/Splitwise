package com.project.splitwise.Controllers.Group;

import com.project.splitwise.Models.Group;
import com.project.splitwise.Service.Group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/splitwise/group")
public class GroupController {

    private static GroupService groupService;

    @Autowired
    GroupController(GroupService groupService){
        GroupController.groupService = groupService;
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createGroup(@RequestBody Group group){
        return groupService.createGroup(group);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllGroups(){
        return groupService.getAllGroups();
    }
}
