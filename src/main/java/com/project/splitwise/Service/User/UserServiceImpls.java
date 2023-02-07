package com.project.splitwise.Service.User;

import com.project.splitwise.DTO.ResponseBody.User.AddUserDTO;
import com.project.splitwise.DTO.ResponseBody.User.AddUserExceptionDTO;
import com.project.splitwise.DTO.ResponseBody.User.UserDTO;
import com.project.splitwise.Models.User;
import com.project.splitwise.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpls implements UserService{
    private static UserRepository userRepository;
    @Autowired
    UserServiceImpls(UserRepository userRepository){
        UserServiceImpls.userRepository = userRepository;
    }

    Optional<User> userAlreadyPresent(User user){
        return userRepository.findByEmail(user.getEmail());
    }

    String generateMessage(String messageType, String email){
        if(messageType == "ERROR"){
            return "user already present with email : " + email;
        }
        return "User with email : " + email + " registered successfully!";
    }

    Timestamp generateTimeStamp(){
        Date date = new Date();
        Timestamp timestamp=new Timestamp(date.getTime());
        return timestamp;
    }

    AddUserDTO generateAddUserDTO(User user, String messageType){
        AddUserDTO addUserDTO = new AddUserDTO(generateTimeStamp(),200,"registered successfully!");
        return addUserDTO;
    }
    AddUserExceptionDTO generateAddUserExceptionDTO(User user, String messageType){
        UserDTO userDTO = new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail());
        AddUserExceptionDTO addUserExceptionDTO = new AddUserExceptionDTO(generateTimeStamp(),400,"register failed!",generateMessage(messageType, user.getEmail()), userDTO);
        return addUserExceptionDTO;
    }
    public ResponseEntity<Object> addUser(User user) {
        Optional<User> usr = userAlreadyPresent(user);
        if(usr.isPresent()){
            AddUserExceptionDTO addUserDTO = generateAddUserExceptionDTO(usr.get(),"ERROR");
            return ResponseEntity.badRequest().body(addUserDTO);
        }else{
            AddUserDTO addUserDTO = generateAddUserDTO(user,"SUCCESS");
            userRepository.save(user);
            return ResponseEntity.ok().body(addUserDTO);
        }
    }
}
