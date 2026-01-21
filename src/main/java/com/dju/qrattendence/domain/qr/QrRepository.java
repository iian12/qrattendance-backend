package com.dju.qrattendence.domain.qr;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QrRepository extends JpaRepository<QrEntity, Long> {
}
