package com.nkosi.programallocator.services;

import com.nkosi.programallocator.dtos.ProgramDto;
import com.nkosi.programallocator.models.Applicant;
import com.nkosi.programallocator.models.Department;
import com.nkosi.programallocator.models.Program;
import com.nkosi.programallocator.repositories.ProgramRepository;
import com.nkosi.programallocator.utils.CodeGeneratorUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;

    private final DepartmentService departmentService;
    private final ApplicantService applicantService;
    private final CodeGeneratorUtil codeGeneratorUtil;


    public Program addProgram(String departmentCode, ProgramDto programDto) {

        Department department = departmentService.getDepartment(departmentCode);

        return programRepository.save(Program.builder()
                .programCode(codeGeneratorUtil.generateProgramID(programDto.getProgramName()))
                .programName(programDto.getProgramName())
                .cutOffPoints(programDto.getCutOffPoints())
                .requiredSubjects(programDto.getRequiredSubjects())
                .department(department)
                .isVacant(programDto.getIsVacant())
                .build()
        );
    }

//
//    public void allocateProgram(){
//
//
//        //fetch all applicants that arent allocated to a program
//        //fetch all vacant programs
//        // each program has specific subject that an applicant should posses e.g Bachelor of Science Honours Degree in Applied Physics one should have an ‘A’ Level pass in Mathematics or Mechanical Mathematics or Pure Mathematics, Additional Mathematics and any one of the following subjects: Physics, Statistics, Geography, Chemistry, Biology and 5 “O” Level passes including English Language  and Mathematics.
//        // each program has cut off points too e.g 10 points
//        // a program belongs to a faculty
//        // the applicant has two  program choices,if the first choice program exists and isVacant then enter loop
//        // if the applicant's points matches the cut-off points and subject criteria then add them to the program applicants
//        //update the isAllocated flag to true for applicant
//        //assign studentCode
//        //else suggest other program for them
//        //exit first if statement
//        //
//
//        List<Applicant> applicants = applicantService.getAllUnAllocatedApplicants();
//        List<Program> programs = programRepository.findAllByIsVacant(true);
//        applicants.forEach(applicant -> {
//
//            programs.forEach(program -> {
//            if(Objects.equals(program.getProgramName(), applicant.getProgramsOfInterest().get(0)) && program.getIsVacant())
//                if(applicant.getPoints() >= 8 ){
//
//                }
//            });
//        });
//    }

    @Async
    public void allocateProgram() {
        // Fetch all unallocated applicants and all vacant programs
        List<Applicant> applicants = applicantService.getAllUnAllocatedApplicants();
        List<Program> programs = programRepository.findAllByIsVacant(true);

        // Attempt to allocate each applicant to one of their preferred programs
        for (Applicant applicant : applicants) {
            Program allocatedProgram = findAndAllocateProgram(applicant, programs);
            if (allocatedProgram == null) {
                // If no preferred program is allocated, suggest an alternative program
                suggestAlternativeProgram(applicant, programs);
            }
        }
    }

    private Program findAndAllocateProgram(Applicant applicant, List<Program> programs) {
        // Iterate through the applicant's program choices
        for (String programName : applicant.getProgramsOfInterest()) {
            for (Program program : programs) {
                // Check if the program is vacant and matches the applicant's choice
                if (program.getProgramName().equals(programName) && program.getIsVacant()) {
                    // Check if the applicant is eligible for the program
                    if (isEligibleForProgram(applicant, program)) {
                        // Allocate the applicant to the program
                        allocateApplicantToProgram(applicant, program);
                        return program;
                    }
                }
            }
        }
        return null;
    }

    private boolean isEligibleForProgram(Applicant applicant, Program program) {
        // Check if the applicant's points meet or exceed the program's cut-off points
        if (applicant.getPoints() < program.getCutOffPoints()) {
            return false;
        }

        List<String> requiredSubjects = program.getRequiredSubjects();
        List<String> applicantSubjects = applicant.getSubjects();

        // Check if the applicant has at least two of the required subjects
        int matchingSubjectsCount = 0;
        for (String subject : requiredSubjects) {
            if (applicantSubjects.contains(subject)) {
                matchingSubjectsCount++;
            }
            if (matchingSubjectsCount >= 2) {
                return true;
            }
        }

        return false;
    }

    private void allocateApplicantToProgram(Applicant applicant, Program program) {
        // Add the applicant to the program
        program.setApplicants(List.of(applicant));
        // Update the applicant's allocated status
        applicant.setIsAllocated(true);
        // Generate and set a unique student code for the applicant
        applicant.setStudentCode(codeGeneratorUtil.generateStudentID());
        // Save the updated program and applicant information
        Program savedProgram = programRepository.save(program);
        // Assign applicant to program
        applicant.setProgram(savedProgram);
        applicantService.updateApplicantStatus(applicant, true);
    }

    private void suggestAlternativeProgram(Applicant applicant, List<Program> programs) {
        // Iterate through the programs to find an alternative
        for (Program program : programs) {
            // Ensure the program is not one of the applicant's original choices
            if (!applicant.getProgramsOfInterest().contains(program.getProgramName())) {
                // Check if the applicant is eligible for the alternative program
                if (isEligibleForAlternativeProgram(applicant, program)) {
                    // Allocate the applicant to the alternative program
                    allocateApplicantToProgram(applicant, program);
                    // Notify the applicant about the alternative program allocation
                    notifyApplicantOfAlternativeProgram(applicant, program);
                    return;
                }
            }
        }
        // Notify the applicant if no suitable alternative programs are found
        notifyApplicantNoAvailablePrograms(applicant);
    }

    private boolean isEligibleForAlternativeProgram(Applicant applicant, Program program) {
        // Check if the applicant's points are within a reasonable range below the cut-off (e.g., 2 points)
        if (applicant.getPoints() < program.getCutOffPoints() - 2) {
            return false;
        }

        List<String> requiredSubjects = program.getRequiredSubjects();
        List<String> applicantSubjects = applicant.getSubjects();

        // Check if the applicant has at least one required subject for alternative programs
        for (String subject : requiredSubjects) {
            if (applicantSubjects.contains(subject)) {
                return true;
            }
        }

        return false;
    }

    private void notifyApplicantOfAlternativeProgram(Applicant applicant, Program program) {
        // Print a notification message for the alternative program allocation
        System.out.println("Applicant " + applicant.getApplicantId() + " has been allocated to an alternative program: " + program.getProgramName());
        // Implement additional notification logic (e.g., email, applicant portal update)
    }

    private void notifyApplicantNoAvailablePrograms(Applicant applicant) {
        // Print a notification message if no suitable alternative programs are found
        System.out.println("No suitable alternative programs found for applicant " + applicant.getApplicantId());
        // Implement additional notification logic (e.g., email, applicant portal update)
    }

}
