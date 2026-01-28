package com.dju.qrattendence.domain.attendance;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // TODO: 날짜별, 과목별 출석 조회 서비스 코드 및 컨트롤러 작성 (오미래)
    /**@GetMapping("/date")
    public ResponseEntity<?> getAttendanceByDate() {
        return ResponseEntity.ok();
    }
     */


    /**@GetMapping("/{lectureName}")
    public ResponseEntity<?> getAttendanceByLectureName() {
        return ResponseEntity.ok();
    }
     */
}
