package com.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long empId;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNo;
    private String department;

}
