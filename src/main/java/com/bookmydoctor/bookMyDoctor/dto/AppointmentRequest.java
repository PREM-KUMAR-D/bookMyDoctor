package com.bookmydoctor.bookMyDoctor.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequest {
    private Long userId;
    private Long doctorId;
    private LocalDate date;
    private LocalTime time;
    private String patientName;
}

