package com.comrade.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class OverTheTopConfig {

    @Bean
    @Conditional(OttDependencyValidator.class)
    public OttPlan ottPlan(){
        log.info("===================OK====================");
        return new OttPlan("Monthly",100);
    }
}
