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

    //비밀번호를 암호화할 경우 사용한다.
    private String encodingPassword(String password){
        return passwordEncoder.encode(password);
    }

    //유저를 생성할 때 사용한다. 비밀번호는 암호화시켜 저장한다.
    public SignUpResponseDto signUp(String username, String password, String usermail){
        User user = new User(username, encodingPassword((password)), usermail);

        User saveUser = userRepository.save(user);

        return new SignUpResponseDto(saveUser.getId(),saveUser.getUsername(), saveUser.getUsermail());
    }

    //로그인시 사용한다. 암호화된 비밀번호가 일치할 경우 로그인 성공
    public LoginResponseDto login(String usermail, String password) {
        User user = userRepository.findUserByUsermailOrElseThrow(usermail);
        matchPassword(user.getId(),password);

        return new LoginResponseDto(user.getId());
    }

    //유저를 유저 고유 번호로 조회할 때 사용한다.
    public UserResponseDto findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"유저가 존재하지 않습니다");
        }
        User findUser = optionalUser.get();
        return new UserResponseDto(findUser.getId(),findUser.getUsername(), findUser.getUsermail());
    }

    //유저를 삭제할 때 사용한다. 만약 유저가 작성한 스케쥴이 남아있을 경우 예외처리를 발생하고 유저 삭제를 실행하지 않는다.
    public void deleteUser(Long id, String password) {
        User findUser = userRepository.findUserByIdOrElseThrow(id);
        if (!scheduleRepository.findByUser_Id(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "사용자와 연관된 스케줄이 존재합니다. 스케줄을 먼저 삭제하세요.");
        }
        matchPassword(id, password);
        userRepository.delete(findUser);
    }

    //암호화 된 비밀번호가 전달받은 비밀번호와 일치한지 확인할 때 사용한다.
    public void matchPassword(Long userid, String password) {
        User user = userRepository.findUserByIdOrElseThrow(userid);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }
    }
}
