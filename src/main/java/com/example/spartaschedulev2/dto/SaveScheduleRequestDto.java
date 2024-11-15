package com.example.spartaschedulev2.dto;

import lombok.Getter;

@Getter
public class SaveScheduleRequestDto {
    private final Long userid;
    private final String title;
    private final String contents;

    public SaveScheduleRequestDto(Long userid, String title, String contents){
        this.userid = userid;
        this.title = title;
        this.contents = contents;
    }
}

