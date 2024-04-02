package com.employeemanagement.service;

import com.employeemanagement.dto.UserDTO;
import com.employeemanagement.exception.UserException;

import java.util.List;

public interface UserService {

    UserDTO getUserById(Long id) throws UserException;

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsersByRole(String userRole) throws UserException;

    UserDTO getUserByUserName(String userName) throws UserException;
}
