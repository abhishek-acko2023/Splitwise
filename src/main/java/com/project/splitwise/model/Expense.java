package com.project.splitwise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "expenses")
public class Expense {
    @Id
    @SequenceGenerator(
            name = "expense_id_sequence",
            sequenceName = "expense_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "expense_id_sequence"
    )
    @Column(
            name = "expense_id",
            nullable = false
    )
    private Integer expense_id ;
    @Column(
            name ="expense_description"
    )
    private String description ="";
    @Column(
            name="expense_amount",
            nullable = false
    )
    private double amount ;

    @Column(
            name = "user_id",
            nullable = false
    )
    private Integer userId ;

    @Column(
            name = "group_id"
    )
    private Integer groupId ;

    @Column(
            name = "expense_partners",
            nullable = false
    )
    private List<Integer> expensePartners = new ArrayList<>();

    @Column(
            name = "split_percentage"
    )
    private List<Double> splits = new ArrayList<>();

    @Column(
            name = "group_name",
            unique = false
    )
    private String groupName ;

    public Expense(String description, double amount, Integer userId, Integer groupId, List<Integer> expensePartners, List<Double> splits, String groupName) {
        this.description = description;
        this.amount = amount;
        this.userId = userId;
        this.groupId = groupId;
        this.expensePartners = expensePartners;
        this.splits = splits;
        this.groupName = groupName;
    }
}
