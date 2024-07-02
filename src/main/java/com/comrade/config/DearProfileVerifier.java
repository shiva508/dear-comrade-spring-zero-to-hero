package com.comrade.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dear")
@Slf4j
public class DearProfileVerifier {

    @PostConstruct
    public void init(){
       log.info("======DEAR=====");
    }
}
