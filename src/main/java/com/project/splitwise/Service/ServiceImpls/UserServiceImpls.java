package com.project.splitwise.Service.ServiceImpls;


import com.project.splitwise.Dao.UserDao;
import com.project.splitwise.Dto.Request.User;
import com.project.splitwise.Dto.Response.Response;
import com.project.splitwise.Service.ServiceInterface.UserService;
import com.project.splitwise.Dto.Response.UserDTO;
import com.project.splitwise.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpls implements UserService {
    public static UserRepo userRepo;
    @Autowired
    public UserServiceImpls(UserRepo userRepo) {
        UserServiceImpls.userRepo = userRepo;
    }

    public Response addUser(User user){
        Response response = new Response("User Added!!" , 200);
        UserDao userDao = new UserDao(user.getUserName(), user.getUserEmail());
        try{
            userRepo.save(userDao);
        }catch (Exception e){
            response.setMessage("User already exist!!");
            response.setStatusCode(0);
        }
       return response;
    }

    public UserDTO convertEntityToDto(UserDao userDao){
        return new UserDTO(userDao.getUserName(), userDao.getUserEmail());
    }
    public List<UserDTO> getUsers(){
        return userRepo.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
    public UserDao getUser(Integer id){
        for(UserDao x: userRepo.findAll()){
            if(x.getUserId().equals(id))
                return x ;
        }
        return new UserDao() ;
    }
    public int updateUser(User user) {
        Optional<UserDao> optionalUserDao = userRepo.findByUserEmail(user.getUserEmail());
        if(optionalUserDao.isPresent()){
            UserDao userDao = optionalUserDao.get();
            if(!user.getUserName().isEmpty()){
                userDao.setUserName(user.getUserName());
                userRepo.save(userDao);
                return 1;
            }
        }
        return -1;
    }
}
