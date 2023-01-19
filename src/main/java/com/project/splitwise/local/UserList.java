package com.project.splitwise.local;

import com.project.splitwise.model.User;
import com.project.splitwise.repository.UserDao;
import com.project.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserList{
    public static List<User> usersList = new ArrayList<>();

    private static UserDao userDao ;
    private static UserList instance ;
    @Autowired
    private UserList(UserDao userDao) {
        UserList.userDao = userDao;
        usersList = userDao.findAll();
//        System.out.println(usersList.size());
        for(User x:usersList){
            System.out.println(x.getUserId());
        }
    }

    public static UserList getInstance(){
        if(instance==null){
            instance = new UserList(UserService.userDao);
        }
        return instance ;
    }


}
