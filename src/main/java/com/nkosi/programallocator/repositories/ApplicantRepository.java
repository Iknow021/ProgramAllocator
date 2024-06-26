package com.nkosi.programallocator.repositories;

import com.nkosi.programallocator.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant,Long>{


    List<Applicant> findAllByIsAllocated(boolean isAllocated);
}
