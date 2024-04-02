package com.employeemanagement.exception;

import com.employeemanagement.constants.ErrorCodesConstants;
import com.employeemanagement.model.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleGenericException(Exception ex) {
        StandardResponse standardResponse = new StandardResponse();
        standardResponse.setResponseCode(500);
        standardResponse.setErrorCode(ErrorCodesConstants.INTERNAL_SERVER_ERROR);
        standardResponse.setResponseMessage(ex.getMessage());
        standardResponse.setResponseDateTime(LocalDateTime.now());
        return new ResponseEntity<>(standardResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<StandardResponse> handleEmployeeException(EmployeeException ex) {
        StandardResponse standardResponse = new StandardResponse();
        standardResponse.setResponseCode(400);
        standardResponse.setErrorCode(ex.getErrorCode());
        standardResponse.setResponseMessage(ex.getErrorMessage());
        standardResponse.setResponseDateTime(LocalDateTime.now());
        return new ResponseEntity<>(standardResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<StandardResponse> handleUserException(UserException ex) {
        StandardResponse standardResponse = new StandardResponse();
        standardResponse.setResponseCode(400);
        standardResponse.setErrorCode(ex.getErrorCode());
        standardResponse.setResponseMessage(ex.getErrorMessage());
        standardResponse.setResponseDateTime(LocalDateTime.now());
        return new ResponseEntity<>(standardResponse, HttpStatus.BAD_REQUEST);
    }
}
