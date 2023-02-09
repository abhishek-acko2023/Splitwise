package com.project.splitwise.Dto.Response.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private Timestamp timestamp;
    private Integer status;
    private String error;

    Timestamp generateTimeStamp (){
        return new Timestamp(System.currentTimeMillis());
    }
    public ExceptionResponse() {
        this.timestamp = generateTimeStamp();
        this.status = 400;
        this.error = "some error occurred";
    }
    public ExceptionResponse(String error) {
        this.timestamp = generateTimeStamp();
        this.status = 400;
        this.error = error;
    }
    public ExceptionResponse(Integer status, String error) {
        this.timestamp = generateTimeStamp();
        this.status = status;
        this.error = error;
    }
}
