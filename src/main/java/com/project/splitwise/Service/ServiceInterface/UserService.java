package com.project.splitwise.Service.ServiceInterface;

import com.project.splitwise.Dao.UserDao;
import com.project.splitwise.Dto.Response.UserDTO;
import com.project.splitwise.Dto.Request.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    ResponseEntity<Object> addUser(User user);
    List<UserDTO> getUsers();
    UserDao getUser(Integer id);
    ResponseEntity<Object> updateUser(User user);

}
