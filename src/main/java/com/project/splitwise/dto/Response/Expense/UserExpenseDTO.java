package com.project.splitwise.Dto.Response.Expense;

import com.project.splitwise.Dto.Response.UserDTO;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserExpenseDTO extends UserDTO {
    private Double splitAmount;

    @Builder
    public UserExpenseDTO(String userName,String userEmail, Double splitAmount){
        super(userName,userEmail);
        this.splitAmount = splitAmount;
    }
}
