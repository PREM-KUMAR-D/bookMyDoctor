package com.bookmydoctor.bookMyDoctor.service;

import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import com.bookmydoctor.bookMyDoctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Doctor> getAllDoctors() {
       return repository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }
}
