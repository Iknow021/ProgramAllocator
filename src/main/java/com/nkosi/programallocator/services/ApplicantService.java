package com.nkosi.programallocator.services;

import com.nkosi.programallocator.dtos.ApplicantDto;
import com.nkosi.programallocator.dtos.ApplicationResponseDto;
import com.nkosi.programallocator.models.Applicant;
import com.nkosi.programallocator.models.Department;
import com.nkosi.programallocator.repositories.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final DepartmentService departmentService;

    public Applicant registerApplicant(ApplicantDto applicantDto) {
        //save applicant details to the database
        return applicantRepository.save(Applicant.builder()
                .firstname(applicantDto.getFirstname())
                .lastname(applicantDto.getLastname())
                .combination(applicantDto.getCombination())
                .points(applicantDto.getPoints())
                .facultyOfInterest(applicantDto.getFacultyOfInterest().name())
                .previousSchool(applicantDto.getPreviousSchool())
                .subjects(applicantDto.getSubjects())
                .programsOfInterest(applicantDto.getProgramsOfInterest())
                .isAllocated(false)
                .build()
        );
    }

    //Fetch applicants that haven't been allocated any program
    public List<Applicant> getAllUnAllocatedApplicants() {
        return applicantRepository.findAllByIsAllocated(false);
    }
    public List<ApplicationResponseDto> getAllAllocatedApplicants(Boolean isAllocated) {

//        Department department = departmentService.getDepartment(departmentCode);
        List<ApplicationResponseDto> allocatedApplicants = new ArrayList<>();
        //fetch all applicants allocated to a program
//        List<Applicant> applicants =  applicantRepository.findAllByProgram_DepartmentAndIsAllocated(department,true);
        List<Applicant> applicants =  applicantRepository.findAllByIsAllocated(isAllocated);

        applicants.forEach(applicant -> {

            allocatedApplicants.add(ApplicationResponseDto.builder()
                            .firstname(applicant.getFirstname())
                            .lastname(applicant.getLastname())
                            .facultyOfInterest(applicant.getFacultyOfInterest())
                            .program(applicant.getProgram().getProgramName())
                            .programCode(applicant.getProgram().getProgramCode())
                            .studentCode(applicant.getStudentCode())
                            .build());

        });

        return allocatedApplicants;
    }


    //Update isAllocated status after the applicant has been given a program
    public void updateApplicantStatus(Applicant applicant, Boolean isAllocated) {

        applicant.setIsAllocated(isAllocated);

        applicantRepository.save(applicant);
    }
}
