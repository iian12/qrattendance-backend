package com.dju.qrattendence.domain.attendance.repository;

import com.dju.qrattendence.domain.attendance.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository
        extends JpaRepository<AttendanceEntity, Long> {

    List<AttendanceEntity> findByDateBetween(LocalDateTime startDay, LocalDateTime endOfDay);

    List<AttendanceEntity> findByLectureName(String lectureName);


}
