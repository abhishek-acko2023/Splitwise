package com.project.splitwise.DTO.ResponseBody.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUserDTO {
    private Timestamp timestamp;
    private Integer status;
    private String message;
}
