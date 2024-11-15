package com.example.spartaschedulev2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    //정규식 표현으로 비밀번호 형식 검증
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*]{8,16}$", message = "비밀번호는 8~16자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다.")
    @Column(nullable = false)
    private String password;

    //@Email 어노테이션을 통해 형식을 검증한다. 이때 @Email은 null이 허용되므로 추가로 nullable을 사용하였다.
    @Email(message = "이메일 형식이 옳지 않습니다")
    @Column(nullable = false)
    private String usermail;

    public User(){}

    public User(String username, String password, String usermail){
        this.username = username;
        this.password = password;
        this.usermail = usermail;
    }
}
