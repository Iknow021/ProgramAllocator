package com.nkosi.programallocator.repositories;

import com.nkosi.programallocator.models.Applicant;
import com.nkosi.programallocator.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,Long>{


    List<Applicant> findAllByIsAllocated(boolean isAllocated);

    @Query(value = "SELECT a.* " +
            "FROM applicant a " +
            "JOIN program p ON a.program_id = p.program_id " +
            "JOIN department d ON p.department_id = d.department_id " +
            "WHERE d.department_code = :departmentCode " +
            "AND a.is_allocated = true", nativeQuery = true)
    List<Applicant> findAllAllocatedApplicantsByDepartmentCode(@Param("departmentCode") String departmentCode);
    List<Applicant> findAllByProgram_DepartmentAndIsAllocated(Department department,Boolean isAllocated);
    List<Applicant> findAllByIsAllocated(Boolean isAllocated);
}
