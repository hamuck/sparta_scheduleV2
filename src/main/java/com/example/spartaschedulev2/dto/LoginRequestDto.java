package com.example.spartaschedulev2.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private final String usermail;
    private final String password;

    public LoginRequestDto(String usermail, String password){
        this.usermail = usermail;
        this.password = password;
    }
}
