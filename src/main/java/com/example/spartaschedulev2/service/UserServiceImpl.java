package com.example.spartaschedulev2.service;

import com.example.spartaschedulev2.config.PasswordEncoder;
import com.example.spartaschedulev2.dto.LoginResponseDto;
import com.example.spartaschedulev2.dto.SignUpResponseDto;
import com.example.spartaschedulev2.dto.UserResponseDto;
import com.example.spartaschedulev2.entity.User;
import com.example.spartaschedulev2.repository.ScheduleRepository;
import com.example.spartaschedulev2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final PasswordEncoder passwordEncoder;

    private String encodingPassword(String password){
        return passwordEncoder.encode(password);
    }

    public SignUpResponseDto signUp(String username, String password, String usermail){
        User user = new User(username, encodingPassword((password)), usermail);

        User saveUser = userRepository.save(user);

        return new SignUpResponseDto(saveUser.getId(),saveUser.getUsername(), saveUser.getUsermail());
    }

    public LoginResponseDto login(String usermail, String password) {
        // 이메일로 사용자 조회
        User user = userRepository.findUserByUsermailOrElseThrow(usermail);

        // 암호화된 비밀번호와 입력된 비밀번호 비교
        matchPassword(user.getId(),password);
        // 로그인 성공 시, 사용자 아이디를 반환
        return new LoginResponseDto(user.getId());
    }
    public UserResponseDto findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"유저가 존재하지 않습니다");
        }
        User findUser = optionalUser.get();
        return new UserResponseDto(findUser.getId(),findUser.getUsername(), findUser.getUsermail());
    }

    public void deleteUser(Long id, String password) {
        User findUser = userRepository.findUserByIdOrElseThrow(id);
        if (!scheduleRepository.findByUser_Id(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용자와 연관된 스케줄이 존재합니다. 스케줄을 먼저 삭제하세요.");
        }
        matchPassword(id, password);
        userRepository.delete(findUser);
    }


    public void matchPassword(Long userid, String password) {
        User user = userRepository.findUserByIdOrElseThrow(userid);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }
    }
}
