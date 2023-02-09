package com.project.splitwise.Dao;

import com.project.splitwise.Dto.Request.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "groups")
public class GroupDao {
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
    private Integer groupCreatedBy;

    @Column(
            name = "group_name"
    )
    private String groupName;

    @Column(
            name = "group_desc"
    )
    private String groupDescription;

    public GroupDao(String groupName, String groupDescription, List<Integer> groupMembers, Integer groupCreatedBy) {
        this.groupMembers = groupMembers;
        this.groupCreatedBy = groupCreatedBy;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }
}
