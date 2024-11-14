package com.example.spartaschedulev2.dto;

import lombok.Getter;

@Getter
public class DeleteScheduleRequestDto {
    private final String password;

    public DeleteScheduleRequestDto(String password){
        this.password = password;
    }
}
