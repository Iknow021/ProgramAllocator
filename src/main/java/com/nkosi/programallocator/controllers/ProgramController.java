package com.nkosi.programallocator.controllers;

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

    @GetMapping("/allocate-programs")
    public ResponseEntity<String> allocateProgram(){
        programService.allocateProgram();
        return ResponseEntity.ok("Students have been successfully assigned to their respective ");
    }

}
