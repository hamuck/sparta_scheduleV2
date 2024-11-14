package com.example.spartaschedulev2.entity;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String password;

    private String usermail;

    public User(){}

    public User(String username, String password, String usermail){
        this.username = username;
        this.password = password;
        this.usermail = usermail;
    }
}