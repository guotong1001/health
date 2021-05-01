package com.tech.health;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tech.health.mapper")
@SpringBootApplication
public class AppServer {

    public static void main(String[] args) {
        SpringApplication.run(AppServer.class, args);
    }

}
