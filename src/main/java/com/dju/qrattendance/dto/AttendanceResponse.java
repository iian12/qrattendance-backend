package com.dju.qrattendance.dto;

import com.dju.qrattendance.domain.attendance.AttendanceEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AttendanceResponse {

    private Long id;
    private String schoolNum; // 학번
    private String name;         // 이름
    private LocalDateTime date;  // 출석 날짜/시간

    // 엔티티 -> DTO 변환
    public AttendanceResponse(AttendanceEntity entity) {
        this.id = entity.getId();
        this.schoolNum = entity.getSchoolNum();
        this.name = entity.getName();
        this.date = entity.getDate();
    }
}