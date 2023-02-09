package com.project.splitwise.Service.ServiceInterface;

import com.project.splitwise.Dao.GroupDao;
import com.project.splitwise.Dto.Request.Group;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GroupService {
    int createGroup(Group group);
    List<GroupDao> getGroups();
    GroupDao groupDetails(Integer groupId);
}
