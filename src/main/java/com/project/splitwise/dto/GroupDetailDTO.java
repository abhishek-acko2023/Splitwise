package com.project.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDetailDTO {
    private List<String> groupMembers ;
    private String groupName ;
    private String createdBy ;
}
