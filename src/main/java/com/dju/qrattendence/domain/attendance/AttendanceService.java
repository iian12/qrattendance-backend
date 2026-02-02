package com.dju.qrattendence.domain.attendance;

import com.dju.qrattendence.domain.attendance.repository.AttendanceRepository;
import com.dju.qrattendence.domain.attendance.repository.AttendanceResponseListByDate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public AttendanceResponse attendance(AttendanceRequest attendanceRequest, Long qrId) {
        AttendanceEntity attendance = AttendanceEntity.builder()
                .qrId(qrId)
                .schoolNumber(attendanceRequest.schoolNumber())
                .name(attendanceRequest.name())
                .build();

        attendanceRepository.save(attendance);

        return new AttendanceResponse(true, "Success");

    }

    public List<AttendanceResponseListByDate> findAttendanceByDate(LocalDate date) {
        LocalDateTime startDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return attendanceRepository.findByDateBetween(startDay, endOfDay)
                .stream()
                .map(attendance -> new AttendanceResponseListByDate(
                        attendance.getSchoolNumber(),
                        attendance.getName(),
                        attendance.getLectureName(),
                        attendance.getDate()
                ))
                .collect(Collectors.toList());
    }

    public List<AttendanceResponseListByDate> findAttendanceByLectureName(String lectureName) {
        return attendanceRepository.findByLectureName(lectureName)
                .stream()
                .map(attendance -> new AttendanceResponseListByDate(
                        attendance.getSchoolNumber(),
                        attendance.getName(),
                        attendance.getLectureName(),
                        attendance.getDate()
                ))
                .collect(Collectors.toList());
    }
}
