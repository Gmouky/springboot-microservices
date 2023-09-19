package com.tuto.departmentservice.controller;

import com.tuto.departmentservice.dto.DepartmentDto;
import com.tuto.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto department = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        List<DepartmentDto> departments = departmentService.getAllDepartment();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long departmentId) {
        DepartmentDto department = departmentService.getDepartment(departmentId);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto,
                                                            @PathVariable Long departmentId) {
        DepartmentDto department = departmentService.updateDepartment(departmentId, departmentDto);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }
    @DeleteMapping("/{departmentId}")
    public String deleteDepartment(@PathVariable long departmentId) {
        departmentService.deleteDepartment(departmentId);
        return "Departement successfully deleted";
    }
}
