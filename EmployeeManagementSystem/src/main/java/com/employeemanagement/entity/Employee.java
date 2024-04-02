package com.employeemanagement.entity;

import com.employeemanagement.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String emailId;
    @Column(nullable = false)
    private String phoneNo;
    @Column(nullable = false)
    private String department;

    public static Employee dtoToDEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setEmpId(employeeDTO.getEmpId());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setEmailId(employeeDTO.getEmailId());
        employee.setPhoneNo(employeeDTO.getPhoneNo());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());

        return employee;
    }
}
