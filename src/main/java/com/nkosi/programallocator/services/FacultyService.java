package com.nkosi.programallocator.services;

import com.nkosi.programallocator.dtos.FacultyDto;
import com.nkosi.programallocator.exceptions.DataNotFoundException;
import com.nkosi.programallocator.models.Faculty;
import com.nkosi.programallocator.repositories.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;


    public Faculty addFaculty(FacultyDto facultyDto){

        return facultyRepository.save(Faculty.builder()
                .facultyCode(facultyDto.getFacultyCode())
                .facultyName(facultyDto.getFacultyName())
                .build()
        );

    }

    public Faculty getFaculty(String facultyCode){

        return facultyRepository.findByFacultyCode(facultyCode)
                .orElseThrow(() ->
                        new DataNotFoundException("Failed to find department with the given facultyCode"
                                .concat(" ").concat(facultyCode)));
    }

}
