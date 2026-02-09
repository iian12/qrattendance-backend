package com.dju.qrattendence.domain.qr;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class QrCreateRequest {
    private String lectureName;
    private LocalDateTime date;
    private Integer expireTime;

    public QrCreateRequest(String lectureName, LocalDateTime date, Integer expireTime) {
        this.lectureName = lectureName;
        this.date = date;
        this.expireTime = expireTime;
    }
}
