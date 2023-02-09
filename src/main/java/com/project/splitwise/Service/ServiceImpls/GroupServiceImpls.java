package com.project.splitwise.Service.ServiceImpls;

import com.project.splitwise.Dao.GroupDao;
import com.project.splitwise.Dto.Request.Group;
import com.project.splitwise.Repository.GroupRepo;
import com.project.splitwise.Service.ServiceInterface.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpls implements GroupService {
    private final GroupRepo groupRepo;
    @Autowired
    public GroupServiceImpls(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public int createGroup(Group group){
        GroupDao groupDao = new GroupDao(
                group.getGroupName(),
                group.getGroupDescription(),
                group.getGroupMembers(),
                group.getGroupCreatedBy()
        );
        try{
            groupRepo.save(groupDao);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }

    public List<GroupDao> getGroups(){
        return groupRepo.findAll() ;
    }

    public GroupDao groupDetails(Integer groupId) {
        Optional<GroupDao> optionalGroupDao = groupRepo.findByGroupId(groupId);
        if(optionalGroupDao.isPresent()){
            GroupDao groupDao = optionalGroupDao.get();
            return groupDao;
        }
        return new GroupDao();
    }
}
