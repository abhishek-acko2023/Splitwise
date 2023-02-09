package com.project.splitwise.Dto.Request;


import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private String groupName;
    private String groupDescription;
    private List<Integer> groupMembers;
    private Integer groupCreatedBy;
}
