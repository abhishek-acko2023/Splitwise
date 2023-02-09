package com.project.splitwise.Dto.Request;

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
public class Expense {
    private String description;
    private double amount;
    private Integer paidBy;
    private List<Integer> expensePartners;
    private Map<Integer,Double> split;
    private String splitType;
    private Integer groupId;

    public Expense(String description, double amount, Integer paidBy, List<Integer> expensePartners) {
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.expensePartners = expensePartners;
    }

    public Expense(String description, double amount, Integer paidBy, List<Integer> expensePartners, String splitType) {
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.expensePartners = expensePartners;
        this.splitType = splitType;
    }

    public Expense(String description, double amount, Integer paidBy, List<Integer> expensePartners, Map<Integer, Double> split, String splitType) {
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.expensePartners = expensePartners;
        this.split = split;
        this.splitType = splitType;
    }
}
