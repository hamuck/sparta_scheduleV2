package com.example.spartaschedulev2.service;

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

    public SignUpResponseDto signUp(String username, String password, String usermail){
        User user = new User(username, password, usermail);

        User saveUser = userRepository.save(user);

        return new SignUpResponseDto(saveUser.getId(),saveUser.getUsername(), saveUser.getUsermail());
    }

    public LoginResponseDto login(String usermail, String password){
        Long index = userRepository.findUserByUserMailAndPasswordForUserid(usermail, password);
        return new LoginResponseDto(index);
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
        userRepository.matchPassword(id, password);
        userRepository.delete(findUser);
    }


}
