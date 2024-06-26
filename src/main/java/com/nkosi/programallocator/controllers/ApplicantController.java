package com.nkosi.programallocator.controllers;

import com.nkosi.programallocator.dtos.ApplicantDto;
import com.nkosi.programallocator.models.Applicant;
import com.nkosi.programallocator.services.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("applicant")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApplicantController {

    private final ApplicantService applicantService;

    @PostMapping("/register")
    public ResponseEntity<Applicant> registerApplicant(@RequestBody ApplicantDto applicantDto){
        return ResponseEntity.ok(applicantService.registerApplicant(applicantDto));
    }
}
