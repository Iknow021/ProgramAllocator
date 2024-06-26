package com.nkosi.programallocator.controllers;

import com.nkosi.programallocator.dtos.ProgramDto;
import com.nkosi.programallocator.models.Program;
import com.nkosi.programallocator.services.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("program")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProgramController {

    private final ProgramService programService;


    @PostMapping
    public ResponseEntity<Program> createProgram(@RequestParam String departmentCode,@RequestBody ProgramDto programDto){
        return ResponseEntity.ok(programService.addProgram(departmentCode, programDto));
    }
    @GetMapping("/allocate-programs")
    public ResponseEntity<String> allocateProgram(){
        programService.allocateProgram();
        return ResponseEntity.ok("Students have been successfully assigned to their respective ");
    }

}
