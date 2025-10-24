package com.bookmydoctor.bookMyDoctor.repository;

import com.bookmydoctor.bookMyDoctor.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave,Long> {
    List<Leave> findByDoctor_DoctorId(Long doctorId);
}
