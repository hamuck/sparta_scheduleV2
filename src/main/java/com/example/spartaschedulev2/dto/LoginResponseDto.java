package com.example.spartaschedulev2.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private final Long userid;

    public LoginResponseDto(Long userid){
        this.userid = userid;
    }
}
