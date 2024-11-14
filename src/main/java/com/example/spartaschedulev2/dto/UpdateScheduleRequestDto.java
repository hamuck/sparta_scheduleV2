package com.example.spartaschedulev2.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {
    private final Long userid;
    private final String password;
    private final String title;
    private final String contents;

    public UpdateScheduleRequestDto(Long userid, String password, String title, String contents){
        this.userid = userid;
        this.password = password;
        this.title = title;
        this.contents = contents;
    }
}
