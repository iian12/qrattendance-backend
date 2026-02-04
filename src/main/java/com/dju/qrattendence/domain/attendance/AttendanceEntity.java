package com.dju.qrattendence.domain.attendance;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_qr_student", columnNames = {"qrId", "schoolNumber"})
        }
)
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long qrId;

    private String schoolNumber;

    private String name;

    private LocalDateTime date;

    @Builder
    public AttendanceEntity(Long qrId, String schoolNumber, String name) {
        this.qrId = qrId;
        this.schoolNumber = schoolNumber;
        this.name = name;
        this.date = LocalDateTime.now();
    }
}
