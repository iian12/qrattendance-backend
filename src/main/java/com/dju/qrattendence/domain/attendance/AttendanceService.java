package com.dju.qrattendence.domain.attendance;

import com.dju.qrattendence.domain.attendance.repository.AttendanceRepository;
import com.dju.qrattendence.domain.attendance.repository.AttendanceResponseListByDate;
import com.dju.qrattendence.domain.qr.QrEntity;
import com.dju.qrattendence.domain.qr.QrRepository;
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
    private final QrRepository qrRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, QrRepository qrRepository) {
        this.attendanceRepository = attendanceRepository;
        this.qrRepository = qrRepository;
    }

    public AttendanceResponse attendance(AttendanceRequest attendanceRequest, Long qrId) {
        QrEntity qrEntity = qrRepository.findById(qrId)
                .orElseThrow(() -> new IllegalArgumentException("QR ID " + qrId + " not found"));

        AttendanceEntity attendance = AttendanceEntity.builder()
                .qrId(qrId)
                .schoolNumber(attendanceRequest.schoolNumber())
                .lectureName(attendanceRequest.lectureName())
                .name(attendanceRequest.name())
                .date(LocalDateTime.now())
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
    public List<AttendanceResponseListByDate> findAttendanceBySchoolNumber(String schoolNumber) {
        return attendanceRepository.findBySchoolNumber(schoolNumber)
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
