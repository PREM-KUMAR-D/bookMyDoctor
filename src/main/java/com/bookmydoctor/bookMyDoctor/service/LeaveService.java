package com.bookmydoctor.bookMyDoctor.service;

import com.bookmydoctor.bookMyDoctor.entity.Doctor;
import com.bookmydoctor.bookMyDoctor.entity.Leave;
import com.bookmydoctor.bookMyDoctor.model.LeaveStatus;
import com.bookmydoctor.bookMyDoctor.model.UserRole;
import com.bookmydoctor.bookMyDoctor.repository.DoctorRepository;
import com.bookmydoctor.bookMyDoctor.repository.LeaveRepository;
import com.bookmydoctor.bookMyDoctor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    public Leave applyLeave(Long doctorId, Leave leave) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + doctorId));
        leave.setDoctor(doctor);
        leave.setStatus(LeaveStatus.PENDING);
        return leaveRepository.save(leave);
    }

    public List<Leave> getLeavesByDoctor(Long doctorId) {
        return leaveRepository.findByDoctor_DoctorId(doctorId);
    }

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public Leave updateLeaveStatus(Long leaveId, LeaveStatus status, Long userId) {
        // check if user exists
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // check if admin
        if (user.getRole() != UserRole.ADMIN) {
            throw new RuntimeException("Access denied: only ADMIN can approve/reject leaves");
        }

        // fetch leave
        var leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave not found"));

        // update status
        leave.setStatus(status);
        return leaveRepository.save(leave);
    }
}
