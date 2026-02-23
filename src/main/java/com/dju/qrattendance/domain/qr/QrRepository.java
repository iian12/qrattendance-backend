package com.dju.qrattendance.domain.qr;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QrRepository extends JpaRepository<QrEntity, Long> {
    Optional<QrEntity> findByToken(String token);

}
