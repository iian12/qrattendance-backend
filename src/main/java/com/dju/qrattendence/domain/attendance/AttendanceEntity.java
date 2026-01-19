package com.dju.qrattendence.domain.attendance;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String SchoolNumber;

    private String Name;

    private LocalDateTime date;

    @Builder
    public AttendanceEntity(String SchoolNumber, String Name) {
        this.SchoolNumber = SchoolNumber;
        this.Name = Name;
        this.date = LocalDateTime.now();
    }
}
