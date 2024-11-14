package com.example.spartaschedulev2.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long userid;
    private final String username;
    private final String usermail;

    public UserResponseDto(Long userid, String username, String usermail){
        this.userid = userid;
        this.username = username;
        this.usermail = usermail;
    }
}
