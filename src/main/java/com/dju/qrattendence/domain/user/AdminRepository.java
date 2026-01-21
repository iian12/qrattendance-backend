package com.dju.qrattendence.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

    // 1. 아이디(username)로 관리자 정보를 찾는 기능 (로그인 시 사용)
    Optional<AdminEntity> findByUsername(String username);

    // 2. 해당 아이디가 이미 존재하는지 확인하는 기능 (회원가입 중복 체크 시 사용)
    boolean existsByUsername(String username);
}
