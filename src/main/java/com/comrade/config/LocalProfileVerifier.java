package com.comrade.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
@Slf4j
public class LocalProfileVerifier {

    @PostConstruct
    public void init(){
       log.info("======LOCAL=====");
    }
}
