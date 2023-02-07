package com.project.splitwise.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "expenseTable")
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
    private UUID expenseId ;
    @Column(
            name="expense_description",
            nullable = false
    )
    private String expenseDescription ;

    @Column(
            name="expense_amount",
            nullable = false
    )
    private Double amount ;

//    @Column(
//            name="paid_by",
//            nullable = false
//    )
//    private User paidBy ;
//
//    @Column(
//            name="expense_members",
//            nullable = false
//    )
//    private List<User> paidTo ;

}
