package com.project.splitwise.Dto.Response.Expense;

import com.project.splitwise.Dto.Request.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {
    private String description;
    private double amount;
    private User paidBy;
    private List<User> expensePartners;
    private List<UserExpenseDTO> split;
    private String splitType;

}
