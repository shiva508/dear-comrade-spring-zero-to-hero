package com.comrade.config.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
@Slf4j
public class H2DriverDependencyValidator implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        log.info("H2DriverDependencyValidator");
        try {
            Class.forName("org.h2.Driver");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
