package com.nkosi.programallocator.repositories;

import com.nkosi.programallocator.models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program,Long> {
    List<Program> findAllByIsVacant(boolean isVacant);
}
