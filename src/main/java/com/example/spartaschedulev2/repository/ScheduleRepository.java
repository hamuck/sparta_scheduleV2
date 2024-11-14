package com.example.spartaschedulev2.repository;

import com.example.spartaschedulev2.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
