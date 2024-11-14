package com.example.spartaschedulev2.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class DeleteScheduleRequestDto {
    private String password;

    @JsonCreator
    public DeleteScheduleRequestDto(String password){
        this.password = password;
    }
}
