package com.bookmydoctor.bookMyDoctor.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="doctors")
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @Column(nullable = false)
    private String name;

    private Integer age;

    @Column(nullable = false,unique = true)
    private String email;

    private String sex;

    @Column(length = 15)
    private String phoneNumber;

    private String speciality;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Appointment> appointments = new ArrayList<>();
}
