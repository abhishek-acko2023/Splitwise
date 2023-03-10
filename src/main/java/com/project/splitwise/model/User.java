package com.project.splitwise.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_id_sequence",
            sequenceName = "user_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_id_sequence"
    )
    @Column(
            name = "user_id",
            nullable = false
    )
    private Integer userId ;
    @Column(
            name = "user_name",
            nullable = false
    )
    private String userName ;
    @Column(
            name ="user_email",
            nullable = false,
            unique = true
    )
    private String userEmail ;

}
