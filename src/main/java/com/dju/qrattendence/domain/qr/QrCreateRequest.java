package com.dju.qrattendence.domain.qr;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class QrCreateRequest {
    private String lectureName;
    private String adminName;
    private LocalDateTime expiredAt;

    public QrCreateRequest(String lectureName, String adminName, LocalDateTime expiredAt) {
        this.lectureName = lectureName;
        this.adminName = adminName;
        this.expiredAt = expiredAt;
    }
}
