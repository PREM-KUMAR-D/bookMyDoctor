package com.bookmydoctor.bookMyDoctor.controller;

import com.bookmydoctor.bookMyDoctor.dto.AppointmentRequest;
import com.bookmydoctor.bookMyDoctor.entity.Appointment;
import com.bookmydoctor.bookMyDoctor.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentRequest req) {
        try {
            Appointment appointment = appointmentService.bookAppointment(
                    req.getDoctorId(),
                    req.getDate(),
                    req.getTime(),
                    req.getPatientName()
            );
            return ResponseEntity.ok(appointment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/by-date")
    public ResponseEntity<List<Appointment>> getAppointmentsByDate(@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date); // parse from query param
        List<Appointment> appointments = appointmentService.getAppointmentsByDate(localDate);
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUser(@PathVariable Long userId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByUser(userId);
        return ResponseEntity.ok(appointments);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

}
