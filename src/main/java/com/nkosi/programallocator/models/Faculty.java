package com.nkosi.programallocator.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
@Table(name = "faculty")
@NoArgsConstructor
@AllArgsConstructor
public class Faculty implements Serializable {

    private static final long serialVersionUID = 14008822301296507L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long facultyId;
    @Column(name = "faculty_code")
    private String facultyCode;
    @Column(name = "faculty_name")
    private String facultyName;
}
