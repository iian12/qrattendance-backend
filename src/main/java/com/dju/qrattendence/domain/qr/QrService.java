package com.dju.qrattendence.domain.qr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public QrCreateResponse createQr(QrCreateRequest req) {
        String token = TokenGenerator.generate(32);

        QrEntity qr = QrEntity.builder()
                .lectureName(req.getLectureName())
                .adminName(req.getAdminName())
                .token(token)
                .expiredAt(req.getExpiredAt())
                .build();

        qrRepository.save(qr);

        String qrText = baseUrl + token;

        String base64Img = qrCodeService.generateBase64Png(qrText, 300);

        return new QrCreateResponse(qr.getId(), qr.getLectureName(), qr.getExpiredAt(), base64Img);
    }
}
