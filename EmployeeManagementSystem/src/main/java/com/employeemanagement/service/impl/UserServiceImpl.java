package com.employeemanagement.service.impl;

import com.employeemanagement.constants.ErrorCodesConstants;
import com.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.dto.UserDTO;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.entity.User;
import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.exception.UserException;
import com.employeemanagement.repository.UserRepository;
import com.employeemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment environment;

    /**
     * This method is used to get user details based on userId
     * @param userId
     * @return UserDTO
     * @throws UserException
     */
    public UserDTO getUserById(Long userId) throws UserException {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() ->
                new UserException(ErrorCodesConstants.USER_NOT_FOUND, environment.getProperty(ErrorCodesConstants.USER_NOT_FOUND))
        );

        return UserDTO.entityToDto(user);
    }

    /**
     * This method is used to add new user
     * @param userDTO
     * @return UserDTO
     */
    public UserDTO createUser(UserDTO userDTO) {
        return UserDTO.entityToDto(userRepository.save(User.dtoToDEntity(userDTO)));
    }

    /**
     * This method is used to fetch all users based
     * @param userRole
     * @return
     * @throws UserException
     */
    public List<UserDTO> getAllUsersByRole(String userRole) throws UserException {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = userRepository.findAllByUserRole(userRole);
        if(users.isEmpty()) {
            throw new UserException(ErrorCodesConstants.USER_NOT_FOUND, environment.getProperty(ErrorCodesConstants.USER_NOT_FOUND));
        }
        for (User user : users) {
            userDTOS.add(UserDTO.entityToDto(user));
        }
        return userDTOS;
    }

    /**
     * This method is used to fetch user details based on userName
     * @param userName
     * @return
     * @throws UserException
     */
    public UserDTO getUserByUserName(String userName) throws UserException{
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        User user = optionalUser.orElseThrow(() ->
                new UserException(ErrorCodesConstants.USER_NOT_FOUND, environment.getProperty(ErrorCodesConstants.USER_NOT_FOUND))
        );
        return UserDTO.entityToDto(user);
    }
}
