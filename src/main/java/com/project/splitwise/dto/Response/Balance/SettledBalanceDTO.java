package com.project.splitwise.Dto.Response.Balance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SettledBalanceDTO {
    private Double netAmount;
    private String message;

}
