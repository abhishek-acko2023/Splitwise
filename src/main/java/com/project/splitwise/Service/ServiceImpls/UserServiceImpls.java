package com.project.splitwise.Service.ServiceImpls;


import com.project.splitwise.Dao.UserDao;
import com.project.splitwise.Dto.Request.User;
import com.project.splitwise.Dto.Response.Exception.ExceptionResponse;
import com.project.splitwise.Dto.Response.Success.SuccessResponse;
import com.project.splitwise.Service.ServiceInterface.UserService;
import com.project.splitwise.Dto.Response.UserDTO;
import com.project.splitwise.Repository.UserRepo;
import com.project.splitwise.Service.Utility.ValidMailCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpls implements UserService {
    public static UserRepo userRepo;
    public static ValidMailCheck validMailCheck;
    @Autowired
    UserServiceImpls(UserRepo userRepo,ValidMailCheck validMailCheck) {
        UserServiceImpls.userRepo = userRepo;
        UserServiceImpls.validMailCheck = validMailCheck;
    }

    public ResponseEntity<Object> addUser(User user){
        UserDao userDao = new UserDao(user.getUserName(), user.getUserEmail());
        try{
            userRepo.save(userDao);
        }catch (Exception e){
            ExceptionResponse err = new ExceptionResponse();
            if(user.getUserEmail() == null){
                err.setError("email not provided!");
            } else if(!validMailCheck.checkValidEmail(user.getUserEmail())){
                err.setError("provided email not valid!");
            }
            else if(user.getUserName() == null){
                err.setError("name cannot be empty!");
            }
            else if(user.getUserName().length() < 3){
                err.setError("name should have atleast 3 characters!");
            }else if(userRepo.findByUserEmail(user.getUserEmail()).isPresent()){
                err.setError("user with same email already exists!");
            }
            return ResponseEntity.badRequest().body(err);
        }
       return ResponseEntity.ok().body(new SuccessResponse("user registered successfully!"));
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
    public ResponseEntity<Object> updateUser(User user) {
        Optional<UserDao> optionalUserDao = userRepo.findByUserEmail(user.getUserEmail());
        if(optionalUserDao.isPresent()){
            UserDao userDao = optionalUserDao.get();
            if(!user.getUserName().isEmpty()){
                userDao.setUserName(user.getUserName());
                userRepo.save(userDao);
                return ResponseEntity.ok().body(new SuccessResponse("user updated successfully!"));
            }
        }
        return ResponseEntity.status(404).body(new ExceptionResponse(404,"no user found with registered email!"));
    }
}
