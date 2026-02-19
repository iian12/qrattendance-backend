package com.dju.qrattendence.global;

import com.dju.qrattendence.domain.user.AdminEntity;
import com.dju.qrattendence.domain.user.AdminRepository;
import com.dju.qrattendence.global.util.AdminProps;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AdminAccountSeeder implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminProps adminProps;

    public AdminAccountSeeder(AdminRepository adminRepository, PasswordEncoder passwordEncoder, AdminProps adminProps) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.adminProps = adminProps;
    }

    @Override
    @Transactional
    public void run(String... args) {
        String username = adminProps.getUsername();
        String rawPassword = adminProps.getPassword();

        if (adminRepository.existsByUsername(username)) {
            return;
        }

        AdminEntity admin = AdminEntity.builder()
                .username(username)
                .name("박민찬")
                .password(passwordEncoder.encode(rawPassword))
                .build();

        adminRepository.save(admin);
    }
}
