package com.dju.qrattendence.domain.attendance;

import com.dju.qrattendence.domain.attendance.repository.AttendanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public AttendanceResponse attendance(AttendanceRequest attendanceRequest) {
        AttendanceEntity attendance = AttendanceEntity.builder()
                .qrId(attendanceRequest.qrId())
                .schoolNumber(attendanceRequest.schoolNumber())
                .name(attendanceRequest.name())
                .build();

        attendanceRepository.save(attendance);

        return new AttendanceResponse(true, "Success");


        // Repository에 저장하고, 반환하는 코드
    }
}
