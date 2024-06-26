package com.nkosi.programallocator.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDto {
    private String programCode;
    private String programName;
    private Boolean isVacant;
    private Integer cutOffPoints;
    private List<String> requiredSubjects;
}
