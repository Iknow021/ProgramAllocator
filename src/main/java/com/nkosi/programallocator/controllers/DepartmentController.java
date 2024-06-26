package com.nkosi.programallocator.controllers;

import com.nkosi.programallocator.dtos.DepartmentDto;
import com.nkosi.programallocator.models.Department;
import com.nkosi.programallocator.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("department")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department>  createDepartment(@RequestParam String facultyCode, @RequestBody DepartmentDto departmentDto){

        return ResponseEntity.ok(departmentService.addDepartment(facultyCode,departmentDto));
    }

    @GetMapping
    public ResponseEntity<Department>  getDepartment(@RequestParam String departmentCode){

        return ResponseEntity.ok(departmentService.getDepartment(departmentCode));
    }
}
