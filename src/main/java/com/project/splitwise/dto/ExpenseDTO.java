package com.project.splitwise.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private String description ;
    private Double amountPaid ;
    private String paidBy ;
    private String groupName ;
}
