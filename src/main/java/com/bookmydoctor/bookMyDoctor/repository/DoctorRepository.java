package com.bookmydoctor.bookMyDoctor.repository;

import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Doctor findByEmail(String email);
    List<Doctor> findBySpecialityIgnoreCase(String speciality);
}
