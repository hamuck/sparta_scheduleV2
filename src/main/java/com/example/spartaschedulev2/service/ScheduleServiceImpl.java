package com.example.spartaschedulev2.service;

import com.example.spartaschedulev2.config.PasswordEncoder;
import com.example.spartaschedulev2.dto.ScheduleResponseDto;
import com.example.spartaschedulev2.entity.Schedule;
import com.example.spartaschedulev2.entity.User;
import com.example.spartaschedulev2.repository.ScheduleRepository;
import com.example.spartaschedulev2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final PasswordEncoder passwordEncoder;

    //스케쥴 저장시 사용한다.
    @Override
    public ScheduleResponseDto save(Long userid, String title, String contents){
        User findeUser = userRepository.findUserByIdOrElseThrow(userid);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUser(findeUser);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(),userid,schedule.getTitle(),schedule.getContents());
    }

    //스케쥴 전체 조회시 사용한다.
    @Override
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

    //스케쥴을 스케쥴 고유 번호로 조회할 시 사용한다.
    @Override
    public ScheduleResponseDto findById(Long id){
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);

        return new ScheduleResponseDto(schedule.getId(), schedule.getUserId(), schedule.getTitle(),schedule.getContents());
    }

    //스케쥴을 수정할 때 사용한다. 트랜잭션을 사용한다. 작성한 유저의 id와 비밀번호가 일치할 경우에 삭제한다.
    @Transactional
    @Override
    public ScheduleResponseDto update(Long id, Long userid, String password, String title, String contents) {
        Schedule schedule = scheduleRepository.findByIdOrElseThrow(id);
        matchPassword(userid, password);
        schedule.updateSchedule(title, contents);

        return new ScheduleResponseDto(schedule.getId(), schedule.getUserId(), schedule.getTitle(), schedule.getContents());
    }

    //스케쥴을 삭제할 경우 사용한다. 작성한 유저의 id와 비밀번호가 일치할 경우에 삭제한다.
    @Override
    public void delete(Long id, String password){

        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        matchPassword(findSchedule.getUserId(),password);
        scheduleRepository.delete(findSchedule);
    }

    //암호화 된 비밀번호가 일치한지 확인하는 메서드
    private void matchPassword(Long userId, String password) {
        User user = userRepository.findUserByIdOrElseThrow(userId);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
    }

}
