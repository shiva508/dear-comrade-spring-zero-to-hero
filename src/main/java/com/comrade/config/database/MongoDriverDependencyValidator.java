package com.comrade.config.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
@Slf4j
public class MongoDriverDependencyValidator implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        log.info("MongoDriverDependencyValidator");
        String enabledDBType=context.getEnvironment().getProperty("dbType");
        return (enabledDBType !=null && enabledDBType.equalsIgnoreCase("MONGODB"));
    }
}
