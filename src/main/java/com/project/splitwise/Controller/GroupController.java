package com.project.splitwise.Controller;

import com.project.splitwise.Dao.GroupDao;
import com.project.splitwise.Dto.Request.Group;
import com.project.splitwise.Service.ServiceImpls.GroupServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/splitwise/group")
public class GroupController {
    private static GroupServiceImpls groupServiceImpls;

    @Autowired
    public GroupController(GroupServiceImpls groupServiceImpls) {
        GroupController.groupServiceImpls = groupServiceImpls;
    }

    @PostMapping("/create")
    public int createGroup(@RequestBody Group group){
        return groupServiceImpls.createGroup(group);
    }

    @GetMapping("/all")
    public List<GroupDao> getGroups(){
        return  groupServiceImpls.getGroups() ;
    }

    @GetMapping("/{groupId}")
    public GroupDao groupDetails(@PathVariable("groupId") Integer groupId){
        return groupServiceImpls.groupDetails(groupId);
    }

}
