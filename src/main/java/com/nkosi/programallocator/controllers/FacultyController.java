package com.nkosi.programallocator.controllers;

import com.nkosi.programallocator.dtos.FacultyDto;
import com.nkosi.programallocator.models.Faculty;
import com.nkosi.programallocator.services.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("faculty")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping()
    public ResponseEntity<Faculty> createFaculty(@RequestBody FacultyDto facultyDto){
        return ResponseEntity.ok(facultyService.addFaculty(facultyDto));
    }

    @GetMapping()
    public ResponseEntity<Faculty> getFaculty(@RequestParam String facultyCode){
        return ResponseEntity.ok(facultyService.getFaculty(facultyCode));
    }
}
