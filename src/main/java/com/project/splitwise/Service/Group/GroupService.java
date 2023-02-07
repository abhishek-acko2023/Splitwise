package com.project.splitwise.Service.Group;

import com.project.splitwise.Models.Group;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface GroupService {
     ResponseEntity<Object> createGroup(Group group);
     ResponseEntity<Object> getAllGroups();
}
