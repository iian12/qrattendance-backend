package com.dju.qrattendence.domain.qr;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface QrRepository extends JpaRepository<QrEntity, Long> {
    Optional<QrEntity> findByToken(String token);

    boolean existsByLectureNameAndExpiredAtAfter(String lectureName, LocalDateTime date);
}
