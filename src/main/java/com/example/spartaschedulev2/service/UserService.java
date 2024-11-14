package com.example.spartaschedulev2.service;

import com.example.spartaschedulev2.dto.SignUpResponseDto;
import com.example.spartaschedulev2.dto.UserResponseDto;

public interface UserService {
    SignUpResponseDto signUp(String username, String password, String usermail);
    UserResponseDto findById(Long id);
    void deleteUser(Long id, String password);
}
