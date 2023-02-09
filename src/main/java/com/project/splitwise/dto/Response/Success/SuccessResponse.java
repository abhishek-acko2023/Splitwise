package com.project.splitwise.Dto.Response.Success;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {
    private Timestamp timestamp;
    private Integer status;
    private String message;
    Timestamp generateTimeStamp (){
       return new Timestamp(System.currentTimeMillis());
    }
    public SuccessResponse(String message) {
        this.timestamp =generateTimeStamp();
        this.status = 200;
        this.message = message;
    }
}
