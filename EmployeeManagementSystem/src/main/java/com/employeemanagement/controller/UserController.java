package com.employeemanagement.controller;


import com.employeemanagement.dto.UserDTO;
import com.employeemanagement.exception.UserException;
import com.employeemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsersByRole(@RequestParam(name = "role") String role) throws UserException {
        List<UserDTO> users = userService.getAllUsersByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) throws UserException {
        UserDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/users/getUserDetails")
    public ResponseEntity<UserDTO> getUserDetails(@RequestParam(name = "userName") String userName) throws UserException {
        UserDTO userDTO = userService.getUserByUserName(userName);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
