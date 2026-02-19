package com.dju.qrattendence;

import com.dju.qrattendence.domain.attendance.AttendanceEntity;
import com.dju.qrattendence.domain.attendance.AttendanceRequest;
import com.dju.qrattendence.domain.attendance.AttendanceResponse;
import com.dju.qrattendence.domain.attendance.AttendanceService;
import com.dju.qrattendence.domain.attendance.repository.AttendanceRepository;
import com.dju.qrattendence.domain.attendance.repository.AttendanceResponseListByDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AttendanceServiceTest {

    @Mock
    private AttendanceRepository attendanceRepository;

    @InjectMocks
    private AttendanceService attendanceService;

    @Test
    void attendanceSaveTest() {
        AttendanceRequest request = new AttendanceRequest("20251191", "C++", "Park Min Chan");
        Long qrId = 1L;

        when(attendanceRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        AttendanceResponse response = attendanceService.attendance(request, qrId);

        assertTrue(response.success());
        assertEquals("Success", response.message());

        verify(attendanceRepository, times(1)).save(any());
    }

    @Test
    void attendanceFindByDateTest() {
        LocalDate date = LocalDate.of(2026, 2, 1);

        AttendanceEntity entity = AttendanceEntity.builder()
                .schoolNum("20251191")
                .name("Park Min Chan")
                .build();

        when(attendanceRepository.findByDateBetween(any(), any()))
                .thenReturn(List.of(entity));

        List<AttendanceResponseListByDate> result = attendanceService.findAttendanceByDate(date);

        assertEquals(1, result.size());
        assertEquals("Park Min Chan", result.get(0).getName());
        assertEquals("20251191", result.get(0).getDate().toString());

        verify(attendanceRepository, times(1))
                .findByDateBetween(any(), any());
    }

    @Test
    void attendanceFindBySchoolNumberTest() {
        AttendanceEntity entity = AttendanceEntity.builder()
                .schoolNum("20251191")
                .name("Park Min Chan")
                .build();

        when(attendanceRepository.findBySchoolNum("20251191")).thenReturn(List.of(entity));

        List<AttendanceResponseListByDate> result = attendanceService.findAttendanceBySchoolNumber("20251191");

        assertEquals(1, result.size());
        assertEquals("Park Min Chan", result.get(0).getName());
        assertEquals("20251191", result.get(0).getSchoolNum());

        verify(attendanceRepository, times(1)).findBySchoolNum("20251191");
    }
    @Test
    void attendanceFindByLectureNameTest() {
        AttendanceEntity entity = AttendanceEntity.builder()
                .schoolNum("20251191")
                .name("Park Min Chan")
                .lectureName("C++")
                .date(LocalDateTime.now())
                .build();

        when(attendanceRepository.findByLectureName("C++")).thenReturn(List.of(entity));

        List<AttendanceResponseListByDate> result = attendanceService.findAttendanceByLectureName("C++");

        assertEquals(1, result.size());
        assertEquals("C++", result.get(0).getLectureName());

        verify(attendanceRepository, times(1)).findByLectureName("C++");
    }
}