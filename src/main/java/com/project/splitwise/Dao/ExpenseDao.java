package com.project.splitwise.Dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "expenses")
public class ExpenseDao {
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
    private String description = "";
    @Column(
            name="expense_amount",
            nullable = false
    )
    private double amount ;
    @Column(
            name = "paid_by",
            nullable = false
    )
    private Integer paidBy ;
    @Column(
            name = "expense_partners",
            nullable = false
    )
    private List<Integer> expensePartners = new ArrayList<>();
    private String splitType;
    private List<Double> split = new ArrayList<>();
    private Integer groupId;
    public ExpenseDao(String description, double amount, Integer paidBy, List<Integer> expensePartners, String splitType, List<Double> split) {
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.expensePartners = expensePartners;
        this.splitType = splitType;
        this.split = split;
    }
    public ExpenseDao(String description, double amount, Integer paidBy, List<Integer> expensePartners, String splitType, List<Double> split, Integer groupId){
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.expensePartners = expensePartners;
        this.splitType = splitType;
        this.split = split;
        this.groupId = groupId;
    }

}
