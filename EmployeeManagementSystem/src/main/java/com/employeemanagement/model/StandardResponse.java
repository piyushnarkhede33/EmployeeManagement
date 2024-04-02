package com.employeemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse {
    private Integer responseCode;
    private String errorCode;
    private String responseMessage;
    private LocalDateTime responseDateTime;
    private Object responseData;
}
