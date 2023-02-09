package com.project.splitwise.Controller;

import com.project.splitwise.Dao.UserDao;
import com.project.splitwise.Dto.Request.User;
import com.project.splitwise.Dto.Response.UserDTO;
import com.project.splitwise.Service.ServiceImpls.UserServiceImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/splitwise/user")
public class UserController {

    private final UserServiceImpls userServiceImpls;
    @Autowired
    public UserController(UserServiceImpls userServiceImpls) {
        this.userServiceImpls = userServiceImpls;
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody User user){
            return userServiceImpls.addUser(user);
    };

    @GetMapping("/all")
    public List<UserDTO> getUsers(){
        return userServiceImpls.getUsers() ;
    }

    @GetMapping("/{userId}")
    public Optional<Object> getUser(@PathVariable("userId") Integer userId){
        return userServiceImpls.getUser(userId);}

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody User user){
        return userServiceImpls.updateUser(user);
    };

}
