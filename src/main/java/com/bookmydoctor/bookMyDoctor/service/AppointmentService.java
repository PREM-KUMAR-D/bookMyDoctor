package com.bookmydoctor.bookMyDoctor.service;

import com.bookmydoctor.bookMyDoctor.entity.Appointment;
import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import com.bookmydoctor.bookMyDoctor.repository.AppointmentRepository;
import com.bookmydoctor.bookMyDoctor.repository.DoctorRepository;
import com.bookmydoctor.bookMyDoctor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;


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

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findByDate(date);
    }
    public List<Appointment> getAppointmentsByUser(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getAppointments();
    }
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

}
