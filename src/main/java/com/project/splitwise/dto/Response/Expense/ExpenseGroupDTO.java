package com.project.splitwise.Dto.Response.Expense;

import com.project.splitwise.Dto.Request.User;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseGroupDTO extends ExpenseDTO {
    private String groupName;
    private String groupDescription;
    @Builder
    public ExpenseGroupDTO(String description, double amount,
                           User paidBy, List<User> expensePartners,
                           List<UserExpenseDTO> split, String splitType,
                           String groupName, String groupDescription){

        super(description,amount,paidBy,expensePartners,split,splitType);
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }
}
