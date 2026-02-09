package com.dju.qrattendence.domain.qr;

import com.dju.qrattendence.global.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class QrService {

    private final QrRepository qrRepository;
    private final QrCodeService qrCodeService;

    @Value("${qr.base-url}")
    private String baseUrl;

    public QrService(QrRepository qrRepository, QrCodeService qrCodeService) {
        this.qrRepository = qrRepository;
        this.qrCodeService = qrCodeService;
    }

    public QrCreateResponse createQr(QrCreateRequest req, CustomUserDetails userDetails) {
        String token = TokenGenerator.generate(32);

        QrEntity qr = QrEntity.builder()
                .lectureName(req.getLectureName())
                .adminName(userDetails.getAdminName())
                .token(token)
                .date(LocalDateTime.now())
                .expireTime(req.getExpireTime())
                .build();

        qrRepository.save(qr);

        String qrText = baseUrl + token;

        String base64Img = qrCodeService.generateBase64Png(qrText, 300);

        return new QrCreateResponse(qr.getId(), qr.getLectureName(), qr.getDate().plusMinutes(req.getExpireTime()), base64Img);
    }
}
