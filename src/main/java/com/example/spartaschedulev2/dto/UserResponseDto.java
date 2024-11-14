package com.example.spartaschedulev2.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private final String username;
    private final String usermail;

    public UserResponseDto(String username, String usermail){
        this.username = username;
        this.usermail = usermail;
    }
}
