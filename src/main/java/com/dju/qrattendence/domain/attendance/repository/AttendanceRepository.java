package com.dju.qrattendence.domain.attendance.repository;

import com.dju.qrattendence.domain.attendance.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository
        extends JpaRepository<AttendanceEntity, Long> {
}
