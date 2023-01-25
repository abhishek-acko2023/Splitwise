package com.project.splitwise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalancelogDTO {
    private String userName ;
    private double netAmount ;
}
