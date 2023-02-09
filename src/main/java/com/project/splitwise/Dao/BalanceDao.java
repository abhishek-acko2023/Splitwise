package com.project.splitwise.Dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "balances")
public class BalanceDao {
    @Id
    @SequenceGenerator(
            name = "balance_id_sequence",
            sequenceName = "balance_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "balance_id_sequence"
    )
    @Column(
            name = "balance_id"
    )
    private Integer balanceId ;

    @Column(
            name = "payer_id",
            nullable = false
    )
    private Integer payerId;
    @Column(
            name = "receiver_id",
            nullable = false
    )
    private Integer receiverId ;
    @Column(
            name = "balance",
            nullable = false
    )
    private double balance ;

    private Integer groupId ;

    public BalanceDao(Integer payerId, Integer receiverId, double balance) {
        this.payerId = payerId;
        this.receiverId = receiverId;
        this.balance = balance;
    }

    public BalanceDao(Integer payerId, Integer receiverId, double balance , Integer groupId) {
        this.payerId = payerId;
        this.receiverId = receiverId;
        this.balance = balance;
        this.groupId = groupId ;
    }
}
