package com.example.spartaschedulev2.controller;

import com.example.spartaschedulev2.common.Const;
import com.example.spartaschedulev2.dto.*;
import com.example.spartaschedulev2.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    //일정 작성시 사용
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody SaveScheduleRequestDto dto){
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(
                dto.getUserid(),
                dto.getTitle(),
                dto.getContents()
        );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    //일정 전체 조회시 사용
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){
        List<ScheduleResponseDto> scheduleResponseDtos = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtos,HttpStatus.OK);
    }

    //일정 단건 아이디 조회시 사용
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id){
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    //일정 단건 수정시 사용, 비밀번호를 함께 받아 검증한 뒤 삭제한다.
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequestDto dto,
            HttpServletRequest request){

        HttpSession session = request.getSession(false);
        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);
        ScheduleResponseDto scheduleResponseDto = scheduleService.update(
                id, userResponseDto.getUserid(), dto.getPassword(), dto.getTitle(), dto.getContents()
        );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    //일정 삭제시 사용, 비밀번호를 함께 받아 검증한 뒤 삭제한다.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id, @RequestBody DeleteScheduleRequestDto dto){
        scheduleService.delete(id,dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
