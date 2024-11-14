package com.example.spartaschedulev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpartaScheduleV2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpartaScheduleV2Application.class, args);
    }

}
