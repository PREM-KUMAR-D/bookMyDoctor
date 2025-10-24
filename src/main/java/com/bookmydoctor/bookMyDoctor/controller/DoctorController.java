package com.bookmydoctor.bookMyDoctor.controller;

import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import com.bookmydoctor.bookMyDoctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private final DoctorService doctorService;


    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/get-all")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }
    @PostMapping("/add")
    public Doctor addDoctor(@RequestBody Doctor d){
        return doctorService.addDoctor(d);
    }
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }
    @GetMapping("/speciality/{speciality}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpeciality(@PathVariable String speciality) {
        List<Doctor> doctors = doctorService.getDoctorsBySpeciality(speciality);
        return ResponseEntity.ok(doctors);
    }




}
