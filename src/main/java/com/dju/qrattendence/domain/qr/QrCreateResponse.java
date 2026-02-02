package com.dju.qrattendence.domain.qr;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class QrCreateResponse {

    private Long qrId;
    private String lectureName;
    private LocalDateTime expiredAt;
    private String imageBase64;

    public QrCreateResponse(Long qrId, String lectureName, LocalDateTime expiredAt, String imageBase64) {
        this.qrId = qrId;
        this.lectureName = lectureName;
        this.expiredAt = expiredAt;
        this.imageBase64 = imageBase64;
    }
}
