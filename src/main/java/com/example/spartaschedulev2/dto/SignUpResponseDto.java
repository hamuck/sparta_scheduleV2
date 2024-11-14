package com.example.spartaschedulev2.dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private final Long id;
    private final String username;
    private final String usermail;

    public SignUpResponseDto(Long id, String username, String usermail){
        this.id = id;
        this.username = username;
        this.usermail = usermail;
    }
}
