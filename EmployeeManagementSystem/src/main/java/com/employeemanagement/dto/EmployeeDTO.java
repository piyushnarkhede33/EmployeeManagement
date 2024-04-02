package com.employeemanagement.dto;

import com.employeemanagement.entity.Employee;
import jakarta.persistence.*;
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

    public static EmployeeDTO entityToDto(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmpId(employee.getEmpId());
        employeeDTO.setDepartment(employee.getDepartment());
        employeeDTO.setEmailId(employee.getEmailId());
        employeeDTO.setPhoneNo(employee.getPhoneNo());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());

        return employeeDTO;
    }
}
