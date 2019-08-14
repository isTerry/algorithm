package com.example.demo;

import com.example.leetcode.Concurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;


@SpringBootApplication
public class DemoApplication {

    @Autowired
    private JavaConfig javaConfig;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

