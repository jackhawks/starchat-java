package com.starchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.starchat.mapper")
@SpringBootApplication(scanBasePackages = "com.starchat")
public class StarChatJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarChatJavaApplication.class, args);
    }
}
