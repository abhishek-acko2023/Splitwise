package com.project.splitwise.DTO.ResponseBody.User;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserExceptionDTO extends AddUserDTO {
    private String error;
    private UserDTO userDetails;

    @Builder
    public AddUserExceptionDTO(Timestamp timestamp, Integer status, String message, String error, UserDTO userDetails) {
        super(timestamp, status,message);
        this.error = error;
        this.userDetails = userDetails;
    }

}
