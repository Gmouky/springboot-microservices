package com.tuto.employeeservice.service;

import com.tuto.employeeservice.dto.APIResponseDto;
import com.tuto.employeeservice.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployee();
    APIResponseDto getEmployee(Long employeeId);
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);
    void deleteEmployee(Long employeeId);
}
