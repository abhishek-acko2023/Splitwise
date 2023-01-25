package com.project.splitwise.service;


import com.project.splitwise.responseModel.Response;
import com.project.splitwise.dto.UserDTO;
import com.project.splitwise.model.User;
import com.project.splitwise.repository.UserDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//import static jdk.internal.loader.AbstractClassLoaderValue.map;


@Service
public class UserService {

    public static UserDao userDao ;
    @Autowired
    private ModelMapper modelMapper ;

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

    public UserDTO getUser(Integer id){
        UserDTO userDTO = new UserDTO() ;
        for(User user :userDao.findAll()){
            if(user.getUserId().equals(id)){
                userDTO = modelMapper.map(user,UserDTO.class);
            }
        }
        return userDTO ;
    }
}
