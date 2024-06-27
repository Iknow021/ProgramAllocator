package com.nkosi.programallocator.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponseDto {
    private String firstname;
    private String lastname;
    private String facultyOfInterest;
    private String program;
    private String programCode;
    private String studentCode;
}
