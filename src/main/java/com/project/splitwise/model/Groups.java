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
@Entity(name = "groups")
public class Groups {
    @Id
    @SequenceGenerator(
            name = "group_id_sequence",
            sequenceName = "group_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_id_sequence"
    )
    @Column(
            name = "group_id",
            nullable = false
    )
    private Integer groupId ;
    @Column(
            name = "group_members"
    )
    private List<Integer> groupMembers = new ArrayList<>() ;

    @Column(
            name = "group_creator"
    )
    private Integer groupCreatedBy ;
}
