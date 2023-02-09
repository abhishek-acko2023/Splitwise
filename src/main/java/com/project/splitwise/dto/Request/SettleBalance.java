package com.project.splitwise.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SettleBalance {
    private String payerEmail;
    private String receiverEmail ;
    private Double settleAmount;
    private Integer groupId;
}
