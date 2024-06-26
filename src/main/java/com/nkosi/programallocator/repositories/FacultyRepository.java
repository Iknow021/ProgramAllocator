package com.nkosi.programallocator.repositories;


import com.nkosi.programallocator.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Optional<Faculty> findByFacultyCode(String facultyCode);
}
