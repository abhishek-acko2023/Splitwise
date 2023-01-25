package com.project.splitwise.service;


import com.project.splitwise.Response;
import com.project.splitwise.dto.UserDTO;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
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


    public UserDTO convertEntityToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
        return userDTO ;
    }
    public List<UserDTO> getUsers(){
        return userDao.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public User getUser(Integer id){
        for(User x:userDao.findAll()){
            if(x.getUserId().equals(id))return x ;
        }
        return new User() ;
    }
}
