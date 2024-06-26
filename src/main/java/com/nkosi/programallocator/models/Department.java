package com.nkosi.programallocator.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
@Table(name = "department")
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {

    private static final long serialVersionUID = 14653690L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Long departmentId;
    @Column(name = "department_code")
    private String departmentCode;
    @Column(name = "department_name")
    private String departmentName;
    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false, foreignKey = @ForeignKey(name = "faculty_id_FK"), referencedColumnName = "faculty_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Faculty faculty;
}
