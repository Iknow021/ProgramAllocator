package com.nkosi.programallocator.services;

import com.nkosi.programallocator.dtos.DepartmentDto;
import com.nkosi.programallocator.exceptions.DataNotFoundException;
import com.nkosi.programallocator.models.Department;
import com.nkosi.programallocator.models.Faculty;
import com.nkosi.programallocator.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final FacultyService facultyService;


    public Department addDepartment(String facultyCode,DepartmentDto departmentDto){

        Faculty faculty = facultyService.getFaculty(facultyCode);

        return departmentRepository.save(Department.builder()
                .departmentCode(departmentDto.getDepartmentCode())//to create util to auto generate dptCode
                .departmentName(departmentDto.getDepartmentName())
                .faculty(faculty)
                .build()
        );
    }


    public Department getDepartment(String departmentCode){
        return departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() ->
                        new DataNotFoundException("Failed to find department with the given deptCode"
                                .concat(" ").concat(departmentCode)));

    }
}
