package com.dju.qrattendence.domain.user;

import com.dju.qrattendence.domain.attendance.AttendanceEntity;
import com.dju.qrattendence.dto.AttendanceResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/attendance")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceList(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<AttendanceEntity> entities = adminService.getAllAttendanceByDate(date);

        List<AttendanceResponse> responseList = entities.stream()
                .map(AttendanceResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest req, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        return ResponseEntity.ok().build();
    }
}