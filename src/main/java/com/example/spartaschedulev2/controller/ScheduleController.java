package com.example.spartaschedulev2.controller;

import com.example.spartaschedulev2.dto.SaveScheduleRequestDto;
import com.example.spartaschedulev2.dto.ScheduleResponseDto;
import com.example.spartaschedulev2.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody SaveScheduleRequestDto dto){
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(
                dto.getId(),
                dto.getTitle(),
                dto.getContents()
        );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtos,HttpStatus.OK);
    }
}
