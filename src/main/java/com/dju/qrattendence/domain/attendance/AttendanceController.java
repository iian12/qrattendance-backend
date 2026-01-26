package com.dju.qrattendence.domain.attendance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping
    public ResponseEntity<AttendanceResponse> attendance(@RequestBody AttendanceRequest attendanceRequest) {
        AttendanceResponse response = attendanceService.attendance(attendanceRequest);
        return ResponseEntity.ok(response);
    }
}
