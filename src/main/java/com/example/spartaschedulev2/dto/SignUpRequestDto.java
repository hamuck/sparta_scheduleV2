package com.example.spartaschedulev2.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private final String username;
    private final String password;
    private final String usermail;

    public SignUpRequestDto(String username, String password, String usermail){
        this.username = username;
        this.password = password;
        this.usermail = usermail;
    }
}
