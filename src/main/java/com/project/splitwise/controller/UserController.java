package com.project.splitwise.controller;

import com.project.splitwise.Response;
import com.project.splitwise.dto.UserDTO;
import com.project.splitwise.repository.UserDao;
import com.project.splitwise.service.UserService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.project.splitwise.model.User;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/splitwise/user")
public class UserController {

    private final UserService userService ;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public Response addUser(@RequestBody User user){
            return userService.addUser(user);
    };

    @GetMapping("/get")
    public List<UserDTO> getUsers(){
        return userService.getUsers() ;
    }

    @GetMapping("/getById/{userId}")
    public User getUser(@PathVariable("userId") Integer userId){
        return userService.getUser(userId);}


}
