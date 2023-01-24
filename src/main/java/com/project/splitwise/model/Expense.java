package com.project.splitwise.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
            name = "group_id",
            nullable = false
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
    private List<Double> splitPercentage = new ArrayList<>();


    public Expense() {
    }

    public Expense(Integer expense_id, String description, double amount, Integer userId, List<Integer> expensePartners, List<Double> splitPercentage, Integer groupId) {
//        System.out.println("Working "+expense_id );
        this.expense_id = expense_id;
        this.description = description;
        this.amount = amount;
        this.userId = userId;
        this.expensePartners = expensePartners;
        this.splitPercentage = splitPercentage;
        this.groupId = groupId ;
    }

    public Integer getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(Integer expense_id) {
        this.expense_id = expense_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getExpensePartners() {
        return expensePartners;
    }

    public void setExpensePartners(List<Integer> expensePartners) {
        this.expensePartners = expensePartners;
    }

    public List<Double> getSplitPercentage() {
        return splitPercentage;
    }

    public void setSplitPercentage(List<Double> splitPercentage) {
        this.splitPercentage = splitPercentage;
    }
}
