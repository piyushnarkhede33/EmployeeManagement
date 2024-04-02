package com.employeemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserException extends Exception {
    public String errorCode;
    public String errorMessage;
}
