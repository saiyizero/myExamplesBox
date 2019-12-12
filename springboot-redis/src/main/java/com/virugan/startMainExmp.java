package com.virugan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class startMainExmp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(startMainExmp.class, args);
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

    }
}
