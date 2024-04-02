package com.employeemanagement.entity;

import com.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.dto.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String emailId;
    @Column(nullable = false)
    private String phoneNo;
    @Column(nullable = false)
    private String userRole;

    public static User dtoToDEntity(UserDTO userDTO){
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEmailId(userDTO.getEmailId());
        user.setPhoneNo(userDTO.getPhoneNo());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserRole(userDTO.getUserRole());

        return user;
    }
}
