package com.dju.qrattendence.domain.user;

import com.dju.qrattendence.domain.attendance.AttendanceEntity;
import com.dju.qrattendence.dto.AttendanceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/attendance")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceList(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<AttendanceEntity> entities = adminService.getAllAttendanceByDate(date);

        List<AttendanceResponse> responseList = entities.stream()
                .map(AttendanceResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
}