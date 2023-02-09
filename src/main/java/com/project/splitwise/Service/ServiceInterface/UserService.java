package com.project.splitwise.Service.ServiceInterface;

import com.project.splitwise.Dao.UserDao;
import com.project.splitwise.Dto.Response.UserDTO;
import com.project.splitwise.Dto.Request.User;
import com.project.splitwise.Dto.Response.Response;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    Response addUser(User user);
    List<UserDTO> getUsers();
    UserDao getUser(Integer id);
    int updateUser(User user);

}
