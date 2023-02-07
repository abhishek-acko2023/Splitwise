package com.project.splitwise.Controllers.User;

import com.project.splitwise.DTO.ResponseBody.User.AddUserDTO;
import com.project.splitwise.Models.User;
import com.project.splitwise.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // get user details
    @GetMapping("/userDetails/{email}")
    public ResponseEntity<Object> getUser(@PathVariable("email") String email){
        return userService.getUser(email);
    }

    // get all users
    @GetMapping("/all")
    public ResponseEntity<Object> allUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Object> deleteUser(@PathVariable ("email") String email){
        return userService.deleteUser(email);
    }

}
