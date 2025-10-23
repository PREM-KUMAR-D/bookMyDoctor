package com.bookmydoctor.bookMyDoctor.service;

import com.bookmydoctor.bookMyDoctor.entity.Appointment;
import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import com.bookmydoctor.bookMyDoctor.repository.AppointmentRepository;
import com.bookmydoctor.bookMyDoctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
@Service
public class AppointmentService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Long doctorId, LocalDate date, LocalTime time, String patientName) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // check if slot is already booked
        boolean isSlotTaken = appointmentRepository.existsByDoctorAndDateAndTime(doctor, date, time);
        if (isSlotTaken) {
            throw new RuntimeException("Slot already booked");
        }

        Appointment appointment = Appointment.builder()
                .doctor(doctor)
                .date(date)
                .time(time)
                .patientName(patientName)
                .status("BOOKED")
                .build();

        return appointmentRepository.save(appointment);
    }
}
