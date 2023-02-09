package com.project.splitwise.Controller;

import com.project.splitwise.Dao.UserDao;
import com.project.splitwise.Dto.Request.User;
import com.project.splitwise.Dto.Response.Response;
import com.project.splitwise.Dto.Response.UserDTO;
import com.project.splitwise.Service.ServiceImpls.UserServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/splitwise/user")
public class UserController {

    private final UserServiceImpls userServiceImpls;
    @Autowired
    public UserController(UserServiceImpls userServiceImpls) {
        this.userServiceImpls = userServiceImpls;
    }

    @PostMapping("/add")
    public Response addUser(@RequestBody User user){
            return userServiceImpls.addUser(user);
    };

    @GetMapping("/all")
    public List<UserDTO> getUsers(){
        return userServiceImpls.getUsers() ;
    }

    @GetMapping("/{userId}")
    public UserDao getUser(@PathVariable("userId") Integer userId){
        return userServiceImpls.getUser(userId);}

    @PostMapping("/update")
    public int updateUser(@RequestBody User user){
        return userServiceImpls.updateUser(user);
    };

}
