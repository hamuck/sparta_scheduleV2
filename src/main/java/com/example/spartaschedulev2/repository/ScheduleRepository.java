package com.example.spartaschedulev2.repository;

import com.example.spartaschedulev2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Optional<Schedule> findByUser_Id(Long userId);

    //id가 존재하지 않을시 예외 발생
    default Schedule findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"존재하지 않는 일정입니다"));
    }

}
