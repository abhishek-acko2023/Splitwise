package com.project.splitwise.DTO.ResponseBody.User;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO extends UserDTO{
    private Timestamp timestamp;
    private String message;
    @Builder
    public UserDetailsDTO(String firstName, String lastName, String email, Timestamp timestamp, String message){
        super(firstName,lastName,email);
        this.timestamp = timestamp;
        this.message = message;
    }
}
