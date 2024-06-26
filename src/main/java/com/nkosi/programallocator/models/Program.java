package com.nkosi.programallocator.models;

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
@Table(name = "program")
@NoArgsConstructor
@AllArgsConstructor
public class Program implements Serializable {

    private static final long serialVersionUID = 100345277102L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long programId;
    @Column(name = "program_code")
    private String programCode;
    @Column(name = "program_name")
    private String programName;
    @Column(name = "is_vacant")
    private Boolean isVacant;
    @Column(name = "cut_off_points")
    private Integer cutOffPoints;

    @Column(name = "required_Subjects")
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> requiredSubjects;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false, foreignKey = @ForeignKey(name = "department_id_FK"), referencedColumnName = "department_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Department department;

    @OneToMany(mappedBy = "program")
    @ToString.Exclude
    private List<Applicant> applicants;
}
