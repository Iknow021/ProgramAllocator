package com.nkosi.programallocator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "applicant")
@NoArgsConstructor
@AllArgsConstructor
public class Applicant implements Serializable {

    private static final long serialVersionUID = 9021191120L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id")
    private Long applicantId;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "combination")
    private String combination;
    @Column(name = "points")
    private Integer points;
    @Column(name = "previous_school")
    private String previousSchool;
    @Column(name = "faculty_of_interest")
    private String facultyOfInterest;
    @Column(name = "program_of_interest")
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> programsOfInterest;
    @Column(name = "subjects")
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> subjects;
    @Column(name = "is_allocated")
    private Boolean isAllocated;
    @Column(name = "student_code")
    private String studentCode;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "program_id", foreignKey = @ForeignKey(name = "program_id_FK"), referencedColumnName = "program_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Program program;

}
