package com.dju.qrattendence.domain.attendance;

import com.dju.qrattendence.domain.attendance.repository.AttendanceResponseListByDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/new/{qrId}")
    public ResponseEntity<AttendanceResponse> attendance(@RequestBody AttendanceRequest attendanceRequest,
                                                         @PathVariable Long qrId) {
        AttendanceResponse response = attendanceService.attendance(attendanceRequest, qrId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AttendanceResponseListByDate>> getAttendanceByDate(@PathVariable LocalDate date) {
        List<AttendanceResponseListByDate> response = attendanceService.findAttendanceByDate(date);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{lectureName}")
    public ResponseEntity<List<AttendanceResponseListByDate>> getAttendanceByLectureName(@PathVariable String lectureName) {
        List<AttendanceResponseListByDate> response = attendanceService.findAttendanceByLectureName(lectureName);
        return ResponseEntity.ok(response);
    }

}
