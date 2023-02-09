package com.project.splitwise.Service.ServiceImpls;

import com.project.splitwise.Dao.GroupDao;
import com.project.splitwise.Dto.Request.Group;
import com.project.splitwise.Dto.Response.Success.SuccessResponse;
import com.project.splitwise.Repository.GroupRepo;
import com.project.splitwise.Service.ServiceInterface.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpls implements GroupService {
    private final GroupRepo groupRepo;
    @Autowired
    public GroupServiceImpls(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    public ResponseEntity<Object> createGroup(Group group){
        GroupDao groupDao = new GroupDao(
                group.getGroupName(),
                group.getGroupDescription(),
                group.getGroupMembers(),
                group.getGroupCreatedBy()
        );
        try{
            groupRepo.save(groupDao);
        }catch (Exception err){
            return ResponseEntity.badRequest().body(err);
        }
        return ResponseEntity.ok(new SuccessResponse("Group Created!!!"));
    }

    public Optional<Object> getGroups(){
        List<GroupDao> groupList = new ArrayList<>() ;
        groupList = groupRepo.findAll() ;
        if(groupList.size()==0) {
            return Optional.of("No groups present!!!");
        }
            return Optional.of(groupList);
        }

    public Optional<Object> groupDetails(Integer groupId) {
        Optional<GroupDao> optionalGroupDao = groupRepo.findByGroupId(groupId);
        if(optionalGroupDao.isPresent()){
            GroupDao groupDao = optionalGroupDao.get();
            return Optional.of(groupDao);
        }
        return Optional.of("Group not found!!");
    }
}
