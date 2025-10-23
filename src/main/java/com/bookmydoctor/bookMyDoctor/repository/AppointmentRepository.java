package com.bookmydoctor.bookMyDoctor.repository;

import com.bookmydoctor.bookMyDoctor.entity.Appointment;
import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    boolean existsByDoctorAndDateAndTime(Doctor doctor, LocalDate date, LocalTime time);
}
