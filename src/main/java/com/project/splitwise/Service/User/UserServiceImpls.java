package com.project.splitwise.Service.User;

import com.project.splitwise.DTO.ResponseBody.User.*;
import com.project.splitwise.Models.User;
import com.project.splitwise.Repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpls implements UserService{
    private static UserRepository userRepository;
    @Autowired
    UserServiceImpls(UserRepository userRepository){
        UserServiceImpls.userRepository = userRepository;
    }

    Optional<User> userAlreadyPresent(String email){
        return userRepository.findByEmail(email);
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
        Optional<User> usr = userAlreadyPresent(user.getEmail());
        if(usr.isPresent()){
            AddUserExceptionDTO addUserDTO = generateAddUserExceptionDTO(usr.get(),"ERROR");
            return ResponseEntity.badRequest().body(addUserDTO);
        }else{
            AddUserDTO addUserDTO = generateAddUserDTO(user,"SUCCESS");
            userRepository.save(user);
            return ResponseEntity.ok().body(addUserDTO);
        }
    }

    public ResponseEntity<Object> getUser(String email){
        Optional<User> usr = userAlreadyPresent(email);
        if(usr.isPresent()){
            User user = usr.get();
            UserDetailsDTO userDetailsDTO = new UserDetailsDTO(user.getFirstName(),user.getLastName(),user.getEmail(),generateTimeStamp(),"user_found!");
            return ResponseEntity.ok().body(userDetailsDTO);
        }else{
            UserDetailsExeptionDTO e = new UserDetailsExeptionDTO(generateTimeStamp(),404, "user not found with email : " + email);
            return ResponseEntity.status(404).body(e);
        }
    }

    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : users){
            UserDTO userDTO = new UserDTO(user.getFirstName(), user.getLastName(),user.getEmail());
            usersDTO.add(userDTO);
        }
        return ResponseEntity.ok().body(usersDTO);
    }

    public ResponseEntity<Object> updateUser(User user) {
        List<User> users = userRepository.findAll();
        System.out.println("userEmail : " + user.getEmail());
        for(User u : users){
            if(u.getEmail().equals(user.getEmail())){
                if(user.getFirstName() != null && !user.getFirstName().isEmpty() ){
                    u.setFirstName(user.getFirstName());
                }
                if(user.getLastName() != null && !user.getLastName().isEmpty()){
                    u.setLastName(user.getLastName());
                }
                userRepository.save(u);
                UpdateUserDTO res = new UpdateUserDTO(generateTimeStamp(),200,"updated successfully!");
                return ResponseEntity.ok().body(res);
            }
        }
        UpdateUserExceptionDTO err = new UpdateUserExceptionDTO(generateTimeStamp(),404, "email not found!");
        return ResponseEntity.status(404).body(err);
    }

    public ResponseEntity<Object> deleteUser(String email) {
        Optional<User> usr = userAlreadyPresent(email);
        if(usr.isPresent()){
                DeleteUserDTO res = new DeleteUserDTO(generateTimeStamp(),200,"deleted successfully!");
                User user = usr.get();
                userRepository.delete(user);
                return ResponseEntity.ok().body(res);
        }else{
                DeleteUserExceptionDTO err = new DeleteUserExceptionDTO(generateTimeStamp(),404,"delete failed due to email not found");
                return ResponseEntity.status(404).body(err);
        }
    }
}
