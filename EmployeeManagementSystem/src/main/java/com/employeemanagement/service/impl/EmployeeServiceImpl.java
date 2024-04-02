package com.employeemanagement.service.impl;

import com.employeemanagement.constants.ErrorCodesConstants;
import com.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.exception.EmployeeException;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Environment environment;

    /**
     * This method is used to fetch employee details based on Employee ID.
     * @param id
     * @return
     * @throws EmployeeException
     */
    public EmployeeDTO getEmployeeById(Long id) throws EmployeeException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        Employee employee = optionalEmployee.orElseThrow(() ->
                new EmployeeException(ErrorCodesConstants.EMPLOYEE_NOT_FOUND, environment.getProperty(ErrorCodesConstants.EMPLOYEE_NOT_FOUND))
        );

        return EmployeeDTO.entityToDto(employee);
    }

    /**
     * This method is used to add employee
     * @param employee
     * @return
     */
    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        return EmployeeDTO.entityToDto(employeeRepository.save(Employee.dtoToDEntity(employee)));
    }

    /**
     * This method is used to fetch all employees.
     * @return
     * @throws EmployeeException
     */
    public List<EmployeeDTO> getAllEmployees() throws EmployeeException {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty()) {
            throw new EmployeeException(ErrorCodesConstants.EMPLOYEE_NOT_FOUND, environment.getProperty(ErrorCodesConstants.EMPLOYEE_NOT_FOUND));
        }
        for (Employee employee : employees) {
            employeeDTOS.add(EmployeeDTO.entityToDto(employee));
        }
        return employeeDTOS;
    }

    /**
     * This method is used to update employee details based on ID.
     * @param id
     * @param employeeDetails
     * @throws EmployeeException
     */
    public void updateEmployee(Long id, Employee employeeDetails) throws EmployeeException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();
            existingEmployee.setFirstName(employeeDetails.getFirstName());
            existingEmployee.setDepartment(employeeDetails.getDepartment());
            employeeRepository.save(existingEmployee);
        } else {
            throw new EmployeeException(ErrorCodesConstants.EMPLOYEE_NOT_FOUND, environment.getProperty(ErrorCodesConstants
                    .EMPLOYEE_NOT_FOUND));
        }
    }

    /**
     * This method is used to delete employees.
     * @param id
     * @throws EmployeeException
     */
    public void deleteEmployee(Long id) throws EmployeeException {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            throw new EmployeeException(ErrorCodesConstants.EMPLOYEE_NOT_FOUND, environment.getProperty(ErrorCodesConstants
                    .EMPLOYEE_NOT_FOUND));
        }
        employeeRepository.deleteById(id);
    }
}
