package com.project.splitwise.Service.ServiceInterface;

import com.project.splitwise.Dto.Response.UserDTO;
import com.project.splitwise.Dto.Request.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    ResponseEntity<Object> addUser(User user);
    List<UserDTO> getUsers();
    Optional<Object> getUser(Integer id);
    ResponseEntity<Object> updateUser(User user);

}
