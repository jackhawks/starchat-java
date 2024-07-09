package com.starchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan("com.starchat.mapper")
public class StarChatJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarChatJavaApplication.class, args);
    }
}
