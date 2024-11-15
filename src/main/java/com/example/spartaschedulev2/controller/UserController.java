package com.example.spartaschedulev2.controller;

import com.example.spartaschedulev2.common.Const;
import com.example.spartaschedulev2.dto.*;
import com.example.spartaschedulev2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto dto){
        SignUpResponseDto signUpResponseDto = userService.signUp(dto.getUsername(),dto.getPassword(),dto.getUsermail());
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request){
        LoginResponseDto responseDto = userService.login(dto.getUsermail(),dto.getPassword());
        Long userId = responseDto.getUserid();

        HttpSession session = request.getSession();

        UserResponseDto loginUser = userService.findById(userId);
        session.setAttribute(Const.LOGIN_USER,loginUser);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto dto = userService.findById(id);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestBody DeleteUserRequestDto dto){
        userService.deleteUser(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
