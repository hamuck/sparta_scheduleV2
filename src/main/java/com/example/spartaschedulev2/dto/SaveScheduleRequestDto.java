package com.example.spartaschedulev2.dto;

import lombok.Getter;

@Getter
public class SaveScheduleRequestDto {
    private final Long id;
    private final String title;
    private final String contents;

    public SaveScheduleRequestDto(Long id, String title, String contents){
        this.id = id;
        this.title = title;
        this.contents = contents;
    }
}

