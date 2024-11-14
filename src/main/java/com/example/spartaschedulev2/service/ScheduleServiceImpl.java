package com.example.spartaschedulev2.service;

import com.example.spartaschedulev2.dto.ScheduleResponseDto;
import com.example.spartaschedulev2.entity.Schedule;
import com.example.spartaschedulev2.entity.User;
import com.example.spartaschedulev2.repository.ScheduleRepository;
import com.example.spartaschedulev2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto save(Long id, String title, String contents){
        User findeUser = userRepository.findUserByIdOrElseThrow(id);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findeUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(),schedule.getTitle(),schedule.getContents());
    }

    @Override
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }
}
