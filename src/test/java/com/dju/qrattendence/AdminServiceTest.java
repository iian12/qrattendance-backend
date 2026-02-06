package com.dju.qrattendence;

import com.dju.qrattendence.domain.attendance.AttendanceEntity;
import com.dju.qrattendence.domain.attendance.repository.AttendanceRepository;
import com.dju.qrattendence.domain.user.AdminRepository;
import com.dju.qrattendence.domain.user.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AdminService adminService;

    @Test
    void getAllAttendanceByDateTest() {
        LocalDate targetDate = LocalDate.of(2025, 5, 5);

        AttendanceEntity entity = AttendanceEntity.builder()
                .schoolNumber("20251191")
                .name("Park Min Chan")
                .build();

        when(attendanceRepository.findByDateBetween(any(), any()))
                .thenReturn(List.of(entity));

        List<AttendanceEntity> result = adminService.getAllAttendanceByDate(targetDate);

        assertEquals(1, result.size());
        assertEquals("Park Min Chan", result.get(0).getName());

        verify(attendanceRepository, times(1)).findByDateBetween(any(), any());
    }
}