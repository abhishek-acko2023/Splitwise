package com.project.splitwise.service;


import com.project.splitwise.Response;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Component
public class UserService {

    public static UserDao userDao ;

    @Autowired
    public UserService(UserDao userDao) {
        UserService.userDao = userDao ;
    }

    public Response addUser(User user){
        Response response = new Response("User Added!!" , 200) ;
        try{
            userDao.save(user);
        }catch (Exception e){
            response.setMessage("User already exist!!");
            response.setStatusCode(0);
        }
       return response;
    }

    public List<User> getUsers(){
        return userDao.findAll();
    }

    public User getUser(Integer id){
        for(User x:userDao.findAll()){
            if(x.getUserId().equals(id))return x ;
        }
        return new User() ;
    }
}
