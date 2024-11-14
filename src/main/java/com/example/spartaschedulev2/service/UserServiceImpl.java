package com.example.spartaschedulev2.service;

import com.example.spartaschedulev2.dto.SignUpResponseDto;
import com.example.spartaschedulev2.dto.UserResponseDto;
import com.example.spartaschedulev2.entity.User;
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

    public SignUpResponseDto signUp(String username, String password, String usermail){
        User user = new User(username, password, usermail);

        User saveUser = userRepository.save(user);

        return new SignUpResponseDto(saveUser.getId(),saveUser.getUsername(), saveUser.getUsermail());
    }

    public UserResponseDto findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"유저가 존재하지 않습니다");
        }
        User findUser = optionalUser.get();
        return new UserResponseDto(findUser.getUsername(), findUser.getUsermail());
    }

    public void deleteUser(Long id, String password) {
        Optional<User> optionalUser = userRepository.findById(id);

        // 유저가 존재하지 않는 경우
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다.");
        }

        User findUser = optionalUser.get();

        // 비밀번호가 일치하지 않는 경우
        if (!findUser.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        // 유저 삭제
        userRepository.delete(findUser);
    }

}
