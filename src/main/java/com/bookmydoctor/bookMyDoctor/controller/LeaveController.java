package com.bookmydoctor.bookMyDoctor.controller;

import com.bookmydoctor.bookMyDoctor.entity.Leave;
import com.bookmydoctor.bookMyDoctor.model.LeaveStatus;
import com.bookmydoctor.bookMyDoctor.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    // Doctor applies for leave
    @PostMapping("/apply/{doctorId}")
    public ResponseEntity<Leave> applyLeave(@PathVariable Long doctorId, @RequestBody Leave leave) {
        return ResponseEntity.ok(leaveService.applyLeave(doctorId, leave));
    }

    // Get leaves for a doctor
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Leave>> getLeavesByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(leaveService.getLeavesByDoctor(doctorId));
    }

    // Admin view all leaves
    @GetMapping("/all")
    public ResponseEntity<List<Leave>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    // Admin approve/reject leave
    @PutMapping("/{leaveId}/status")
    public ResponseEntity<?> updateLeaveStatus(@PathVariable Long leaveId,
                                               @RequestParam LeaveStatus status,
                                               @RequestParam Long userId) {
        try {
            return ResponseEntity.ok(leaveService.updateLeaveStatus(leaveId, status, userId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
