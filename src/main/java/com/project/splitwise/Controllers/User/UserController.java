package com.project.splitwise.Controllers.User;

import com.project.splitwise.DTO.ResponseBody.User.AddUserDTO;
import com.project.splitwise.Models.User;
import com.project.splitwise.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/splitwise/user")
public class UserController {
    private static UserService userService;
    @Autowired
    UserController(UserService userService){
        UserController.userService = userService;
    }

    // Add New User
    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        return userService.addUser(user);
    }
}
