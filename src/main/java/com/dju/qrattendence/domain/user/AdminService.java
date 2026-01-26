package com.dju.qrattendence.domain.user;

import com.dju.qrattendence.domain.attendance.AttendanceEntity;
import com.dju.qrattendence.domain.attendance.AttendanceRepository; // 출석 레포지토리 주입 필요
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService {

    private final AdminRepository adminRepository;
    private final AttendanceRepository attendanceRepository; // 출석 데이터 조회를 위해 필요

    /**
     * [관리자 기능] 특정 날짜의 전체 출석 명단 조회
     */
    public List<AttendanceEntity> getAllAttendanceByDate(LocalDate date) {
        // LocalDate를 해당 날짜의 시작(00:00:00)과 끝(23:59:59)으로 변환
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        // 출석 레포지토리에서 해당 범위의 데이터를 가져옴
        return attendanceRepository.findByDateBetween(startOfDay, endOfDay);
    }

    // 이외에 관리자 등록, 관리자 정보 수정 등의 로직이 여기에 포함됩니다.
}