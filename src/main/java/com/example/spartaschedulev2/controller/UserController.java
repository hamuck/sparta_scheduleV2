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

    //회원가입시 사용
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto dto){
        SignUpResponseDto signUpResponseDto = userService.signUp(dto.getUsername(),dto.getPassword(),dto.getUsermail());
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    //로그인시 사용. usermail과 password가 일치할 시 session키를 발급한다.
    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request){
        LoginResponseDto responseDto = userService.login(dto.getUsermail(),dto.getPassword());
        Long userId = responseDto.getUserid();

        HttpSession session = request.getSession();

        UserResponseDto loginUser = userService.findById(userId);
        session.setAttribute(Const.LOGIN_USER,loginUser);
    }

    //로그아웃 시 사용. 세션키가 남아있을 경우 세션키를 지운다.
    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
    }

    //유저를 아이디로 단건 조회할 때 사용
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id){
        UserResponseDto dto = userService.findById(id);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //유저를 아이디로 단건 조회한 뒤 삭제한다. 비밀번호를 함께 전달받아 일치할 경우에만 삭제한다.
    //만약 해당 사용자가 작성한 일정이 남아있는 경우 삭제되지 않는다.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestBody DeleteUserRequestDto dto){
        userService.deleteUser(id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
