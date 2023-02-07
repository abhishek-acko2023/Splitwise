package com.project.splitwise.Service.User;

import com.project.splitwise.DTO.ResponseBody.User.AddUserDTO;
import com.project.splitwise.Models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> addUser(User user);
    ResponseEntity<Object> getUser(String email);
    ResponseEntity<Object> getAllUsers();
    ResponseEntity<Object> updateUser(User user);
    ResponseEntity<Object> deleteUser(String email);
}
