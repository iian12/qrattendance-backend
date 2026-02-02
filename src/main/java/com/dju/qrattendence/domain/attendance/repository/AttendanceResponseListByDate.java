package com.dju.qrattendence.domain.attendance.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AttendanceResponseListByDate {
    private String schoolNum;

    private String name;

    private String lectureName;

    private LocalDateTime date;

    public AttendanceResponseListByDate(String schoolNum, String name, String lectureName, LocalDateTime date) {
        this.schoolNum = schoolNum;
        this.name = name;
        this.lectureName = lectureName;
        this.date = date;
    }
}
