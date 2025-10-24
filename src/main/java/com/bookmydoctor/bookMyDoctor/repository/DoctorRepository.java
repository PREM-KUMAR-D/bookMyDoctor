package com.bookmydoctor.bookMyDoctor.repository;

import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Doctor findByEmail(String email);
    List<Doctor> findBySpecialityIgnoreCase(String speciality);
    Page<Doctor> findByNameContainingIgnoreCaseOrSpecialityContainingIgnoreCase(
            String name, String specialization, Pageable pageable
    );
}
