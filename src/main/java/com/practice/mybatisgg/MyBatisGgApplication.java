package com.practice.mybatisgg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.practice.mybatisgg.Mapper")
public class MyBatisGgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisGgApplication.class, args);
    }

}
