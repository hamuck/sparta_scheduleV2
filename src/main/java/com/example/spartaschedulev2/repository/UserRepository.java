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
    Optional<User> findUserByUsermailAndPassword(String usermail, String password);

    default User findUserByIdOrElseThrow(Long id) {
        return findUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저 입니다"));
    }

    default Long findUserByUserMailAndPasswordForUserid(String usermail, String password){
        User user = findUserByUsermailAndPassword(usermail, password)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일 또는 비밀번호가 일치하지 않습니다"));
        return user.getId();
    }

    default void matchPassword(Long userid, String password){
        User user = findUserByIdOrElseThrow(userid);
        if (!user.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다");
        }
    }

}
