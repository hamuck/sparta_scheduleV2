package com.example.spartaschedulev2.service;

import com.example.spartaschedulev2.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto save(Long id, String title, String contents);
    List<ScheduleResponseDto> findAll();
}
