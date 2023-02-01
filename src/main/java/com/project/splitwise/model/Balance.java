package com.project.splitwise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(
            name = "group_id",
            nullable = false
    )
    private Integer groupId ;
    @Column(
            name = "group_name",
            unique = false ,
            nullable = true
    )
    private String groupName ;

    public Balance(Integer donorId, Integer receiverId, double balance, Integer groupId, String groupName) {
        this.donorId = donorId;
        this.receiverId = receiverId;
        this.balance = balance;
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Balance(Integer donorId, Integer receiverId, double balance) {
        this.donorId = donorId;
        this.receiverId = receiverId;
        this.balance = balance;
    }
}
