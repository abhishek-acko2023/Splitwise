package com.project.splitwise.model;

import jakarta.persistence.*;

@Entity(name = "balances")
public class Balance {
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
            name = "donor_id",
            nullable = false
    )
    private Integer donorId;
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

    public Balance() {
    }

    public Balance(Integer donorId, Integer receiverId, double balance) {
        this.donorId = donorId;
        this.receiverId = receiverId;
        this.balance = balance;
    }

    public Integer getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Integer balanceId) {
        this.balanceId = balanceId;
    }

    public Integer getDonorId() {
        return donorId;
    }

    public void setDonorId(Integer donorId) {
        this.donorId = donorId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
