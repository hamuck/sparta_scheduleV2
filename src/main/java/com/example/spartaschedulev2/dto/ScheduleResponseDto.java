package com.example.spartaschedulev2.dto;

import com.example.spartaschedulev2.entity.Schedule;
import com.example.spartaschedulev2.entity.User;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final Long userid;
    private final String title;
    private final String contents;

    public ScheduleResponseDto(Long id, Long userid, String title, String contents){
        this.id = id;
        this.userid = userid;
        this.title = title;
        this.contents = contents;
    }

    public static ScheduleResponseDto toDto(Schedule schedule){
        User user = schedule.getUser();
        return new ScheduleResponseDto(schedule.getId(),user.getId(),schedule.getTitle(),schedule.getContents());
    }
}
