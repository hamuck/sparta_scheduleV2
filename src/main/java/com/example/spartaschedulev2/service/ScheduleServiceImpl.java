package com.example.spartaschedulev2.service;

import com.example.spartaschedulev2.dto.ScheduleResponseDto;
import com.example.spartaschedulev2.entity.Schedule;
import com.example.spartaschedulev2.entity.User;
import com.example.spartaschedulev2.repository.ScheduleRepository;
import com.example.spartaschedulev2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public ScheduleResponseDto save(Long userid, String title, String contents){
        User findeUser = userRepository.findUserByIdOrElseThrow(userid);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findeUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(),userid,schedule.getTitle(),schedule.getContents());
    }

    @Override
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

    @Override
    public ScheduleResponseDto findById(Long id){
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        User writer = schedule.getUser();

        return new ScheduleResponseDto(schedule.getId(), schedule.getUserId(), schedule.getTitle(),schedule.getContents());
    }

    @Transactional
    @Override
    public ScheduleResponseDto update(Long id, Long userid, String password, String title, String contents){
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        userRepository.matchPassword(userid,password);

        schedule.updateSchedule(title,contents);

        return new ScheduleResponseDto(schedule.getId(), schedule.getUserId(),schedule.getTitle(),schedule.getContents());
    }

    @Override
    public void delete(Long id, String password){

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        userRepository.matchPassword(findSchedule.getUserId(),password);
        scheduleRepository.delete(findSchedule);
    }

}
