package com.comrade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(basePackages = {"com.comrade"})
public class MilkySweetyLlmServiceApplication {

    @Autowired
    MilkySweetyLlmClient milkySweetyLlmClient;
    public static void main(String[] args) {
        SpringApplication.run(MilkySweetyLlmServiceApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(){
        return  web->{

        };
    }
}