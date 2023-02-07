package com.project.splitwise.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;

    @ManyToMany(mappedBy = "groupMembers", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Group> groups;

}
