package com.project.splitwise.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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

    public Groups(Integer groupId, ArrayList<Integer> groupMembers , Integer groupCreatedBy) {
        this.groupId = groupId;
        this.groupMembers = groupMembers;
        this.groupCreatedBy = groupCreatedBy;
    }

    public Groups() {
    }

    public Integer getGroupCreatedBy() {
        return groupCreatedBy;
    }

    public void setGroupCreatedBy(Integer groupCreatedBy) {
        this.groupCreatedBy = groupCreatedBy;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(List<Integer> groupMembers) {
        this.groupMembers = groupMembers;
    }
}
