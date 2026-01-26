package com.dju.qrattendence.domain.attendance;

public record AttendanceRequest(String schoolNumber, String name, Long qrId) { }
