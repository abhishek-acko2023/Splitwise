package com.project.splitwise.service;

import com.project.splitwise.model.Groups;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupService {
    private final GroupDao groupDao ;
    @Autowired
    public GroupService(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public void createGroup(Groups groups){
        groupDao.save(groups);
    }

    public List<Groups> getGroups(){
        return groupDao.findAll() ;
    }
}
