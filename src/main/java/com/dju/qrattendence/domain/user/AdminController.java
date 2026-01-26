package com.dju.qrattendence.domain.user;

import com.dju.qrattendence.domain.attendance.AttendanceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin") // 관리자 관련 기능은 모두 /api/admin으로 시작하게 설정
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * [관리자 기능] 특정 날짜별 출석 명단 조회
     * 호출 방법: GET http://localhost:8080/api/admin/attendance?date=2026-01-24
     */
    @GetMapping("/attendance")
    public ResponseEntity<List<AttendanceEntity>> getAttendanceListByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        // 1. 서비스를 통해 해당 날짜의 출석 데이터를 가져옴
        List<AttendanceEntity> result = adminService.getAllAttendanceByDate(date);

        // 2. HTTP 상태 코드 200(OK)과 함께 결과 데이터(JSON) 반환
        return ResponseEntity.ok(result);
    }

    // 이후에 관리자 로그인(@PostMapping("/login")) 등의 코드가 여기에 추가될 수 있습니다.
}