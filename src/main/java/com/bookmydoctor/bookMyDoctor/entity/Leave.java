package com.bookmydoctor.bookMyDoctor.entity;

import com.bookmydoctor.bookMyDoctor.model.LeaveStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "leaves")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveId;
    @Column(nullable = false)
    private String reason;
    @Column(nullable = false)
    private LocalDate fromDate;
    @Column(nullable = false)
    private LocalDate  toDate;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
}
