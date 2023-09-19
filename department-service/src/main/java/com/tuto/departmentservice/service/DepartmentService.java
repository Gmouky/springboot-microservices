package com.tuto.departmentservice.service;

import com.tuto.departmentservice.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartment();
    DepartmentDto getDepartment(Long departmentId);
    DepartmentDto updateDepartment(long departmentId, DepartmentDto departmentDto);
    void deleteDepartment(long departmentId);
}
