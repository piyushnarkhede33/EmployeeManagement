package com.employeemanagement.service;

import com.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.exception.EmployeeException;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO getEmployeeById(Long id) throws EmployeeException;

    EmployeeDTO createEmployee(EmployeeDTO employee);

    List<EmployeeDTO> getAllEmployees() throws EmployeeException;

    void updateEmployee(Long id, Employee employeeDetails) throws EmployeeException;

    void deleteEmployee(Long id) throws EmployeeException;
}
