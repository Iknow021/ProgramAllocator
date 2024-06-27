package com.nkosi.programallocator.dtos;

import com.nkosi.programallocator.enums.Faculty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantDto {
    private String firstname;
    private String lastname;
    private String combination;
    private Integer points;
    private String previousSchool;
//    @Enumerated(EnumType.STRING)
    private Faculty facultyOfInterest;
    private List<String> programsOfInterest;
    private List<String> subjects;

}
