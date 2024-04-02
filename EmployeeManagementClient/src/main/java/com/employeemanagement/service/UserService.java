package com.employeemanagement.service;

import com.employeemanagement.dto.UserDTO;

public interface UserService {
    UserDTO getByUsername(String username);
}
