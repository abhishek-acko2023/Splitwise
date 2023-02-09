package com.project.splitwise.Dto.Response.Balance;

import com.project.splitwise.Dto.Response.UserDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDTO {
    private UserDTO payer;
    private UserDTO receiver;
    private Double amount;
    private String message;
}
