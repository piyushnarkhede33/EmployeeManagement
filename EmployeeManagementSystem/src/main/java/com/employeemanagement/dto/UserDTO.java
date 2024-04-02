package com.employeemanagement.dto;

import com.employeemanagement.entity.User;
import jakarta.persistence.*;
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
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNo;
    private String userRole;

    public static UserDTO entityToDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmailId(user.getEmailId());
        userDTO.setPhoneNo(user.getPhoneNo());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUserRole(user.getUserRole());

        return userDTO;
    }
}
