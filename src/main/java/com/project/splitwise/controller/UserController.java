package com.project.splitwise.controller;

import com.project.splitwise.responseModel.Response;
import com.project.splitwise.dto.UserDTO;
import com.project.splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.splitwise.model.User;

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

    @GetMapping("/")
    public List<UserDTO> getUsers(){
        return userService.getUsers() ;
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable("userId") Integer userId){
        return userService.getUser(userId);}


}
