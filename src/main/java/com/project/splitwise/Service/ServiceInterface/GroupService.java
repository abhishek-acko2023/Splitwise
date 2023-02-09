package com.project.splitwise.Service.ServiceInterface;

import com.project.splitwise.Dto.Request.Group;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface GroupService {
    ResponseEntity<Object> createGroup(Group group);
    Optional<Object> getGroups();
    Optional<Object> groupDetails(Integer groupId);
}
