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

    private String token;

    private LocalDateTime date;

    private Integer expireTime;

    @Builder
    public QrEntity(String lectureName, String adminName, String token, LocalDateTime date, Integer expireTime) {
        this.lectureName = lectureName;
        this.adminName = adminName;
        this.token = token;
        this.date = date;
        this.expireTime = expireTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(date.plusMinutes(expireTime));
    }
}
