package com.dju.qrattendence.domain.qr;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lectureName; // varchar(255)

    private String adminName; // varchar(255)

    private LocalDateTime date;

    private LocalDateTime expiredAt;

    @Builder
    public QrEntity(String lectureName, String adminName, LocalDateTime expiredAt) {
        this.lectureName = lectureName;
        this.adminName = adminName;
        this.date = LocalDateTime.now();
        this.expiredAt = expiredAt;
    }
}
