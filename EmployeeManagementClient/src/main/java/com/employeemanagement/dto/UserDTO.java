package com.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String userName;
    private String password;
    private String emailId;
    private String phoneNo;
    private String userRole;
    private String firstName;
    private String lastName;
}
