package com.bookmydoctor.bookMyDoctor.service;

import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import com.bookmydoctor.bookMyDoctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;

import java.util.List;
@Service
public class DoctorService {

    @Autowired
    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public Doctor addDoctor(Doctor doctor){
        return repository.save(doctor);
    }

    public Page<Doctor> getAllDoctors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return repository.findAll(pageable);
    }

    public Doctor getDoctorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    public List<Doctor> getDoctorsBySpeciality(String speciality) {
        return repository.findBySpecialityIgnoreCase(speciality);
    }

    public Page<Doctor> searchDoctors(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return repository.findByNameContainingIgnoreCaseOrSpecialityContainingIgnoreCase(
                query, query, pageable
        );
    }
}
