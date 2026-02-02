package com.dju.qrattendence.domain.qr;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qr")
public class QrController {

    private final QrService qrService;

    public QrController(QrService qrService) {
        this.qrService = qrService;
    }

    @PostMapping
    public ResponseEntity<QrCreateResponse> createQr(@RequestBody QrCreateRequest qrCreateRequest) {
        QrCreateResponse qrCreateResponse = qrService.createQr(qrCreateRequest);
        return ResponseEntity.ok(qrCreateResponse);
    }
}
