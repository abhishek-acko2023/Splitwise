package com.project.splitwise.Controller;

import com.project.splitwise.Dao.GroupDao;
import com.project.splitwise.Dto.Request.Group;
import com.project.splitwise.Service.ServiceImpls.GroupServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/splitwise/group")
public class GroupController {
    private static GroupServiceImpls groupServiceImpls;

    @Autowired
    public GroupController(GroupServiceImpls groupServiceImpls) {
        GroupController.groupServiceImpls = groupServiceImpls;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createGroup(@RequestBody Group group){
        return groupServiceImpls.createGroup(group);
    }

    @GetMapping("/all")
    public Optional<Object>  getGroups(){
        return  groupServiceImpls.getGroups() ;
    }

    @GetMapping("/{groupId}")
    public Optional<Object> groupDetails(@PathVariable("groupId") Integer groupId){
        return groupServiceImpls.groupDetails(groupId);
    }

}
