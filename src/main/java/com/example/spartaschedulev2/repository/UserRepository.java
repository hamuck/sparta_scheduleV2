package com.example.spartaschedulev2.repository;

import com.example.spartaschedulev2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsermail(String usermail);

    //id로 찾을 유저가 존재하지 않을시 예외처리
    default User findUserByIdOrElseThrow(Long id) {
        return findUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저 입니다"));
    }
    //id로 찾을 유저가 존재하지 않을시 예외처리
    default User findUserByUsermailOrElseThrow(String usermail){
        return findUserByUsermail(usermail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저 입니다"));
    }
}
