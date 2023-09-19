package com.tuto.departmentservice.service.impl;

import com.tuto.departmentservice.dto.DepartmentDto;
import com.tuto.departmentservice.entity.Department;
import com.tuto.departmentservice.exception.ResourceNotFoundException;
import com.tuto.departmentservice.repository.DepartmentRepository;
import com.tuto.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private ModelMapper modelMapper;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);
        Department savedDepartment = departmentRepository.save(department);
        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public List<DepartmentDto> getAllDepartment() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> modelMapper.map(department, DepartmentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartment(Long departmentId) {
        Department department = getDepartmentById(departmentId);
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public DepartmentDto updateDepartment(long departmentId, DepartmentDto departmentDto) {
        Department department = getDepartmentById(departmentId);
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        department.setDepartmentCode(departmentDto.getDepartmentCode());

        return modelMapper.map(departmentRepository.save(department), DepartmentDto.class);
    }

    @Override
    public void deleteDepartment(long departmentId) {
        Department department = getDepartmentById(departmentId);
        departmentRepository.delete(department);
    }

    private Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", "id", departmentId));
    }
}
