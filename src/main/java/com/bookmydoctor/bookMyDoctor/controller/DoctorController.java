package com.bookmydoctor.bookMyDoctor.controller;

import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import com.bookmydoctor.bookMyDoctor.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private final DoctorService doctorService;


    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<Doctor>> getAllDoctors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("Getting all the doctors");
        return ResponseEntity.ok(doctorService.getAllDoctors(page, size));
    }
    @PostMapping("/add")
    public Doctor addDoctor(@RequestBody Doctor d){
        return doctorService.addDoctor(d);
    }
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }
    @GetMapping("/search")
    public ResponseEntity<Page<Doctor>> searchDoctors(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(doctorService.searchDoctors(query, page, size));
    }

    @GetMapping("/speciality/{speciality}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpeciality(@PathVariable String speciality) {
        List<Doctor> doctors = doctorService.getDoctorsBySpeciality(speciality);
        return ResponseEntity.ok(doctors);
    }




}
