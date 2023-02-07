package com.project.splitwise.Service.Group;

import com.project.splitwise.Models.Group;
import com.project.splitwise.Repository.Group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupServiceImpls implements GroupService{

    private static GroupRepository groupRepository;

    @Autowired
    GroupServiceImpls(GroupRepository groupRepository){
        GroupServiceImpls.groupRepository = groupRepository;
    }
    public ResponseEntity<Object> createGroup(Group group) {
        groupRepository.save(group);
        return ResponseEntity.ok().body("Group Created successfully!");
    }

    public ResponseEntity<Object> getAllGroups() {
        List<Group> allGroups = groupRepository.findAll();
        return ResponseEntity.badRequest().body(allGroups);
    }
}
