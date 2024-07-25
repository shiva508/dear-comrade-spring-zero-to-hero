package com.comrade.config.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import java.util.Map;

@Slf4j
public class DatabaseDriverTypeCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(DatabaseDriverType.class.getName());
        String type = (String) attributes.get("value");
        log.info("Expected database Type = {}",type);
        String enabledDBType = context.getEnvironment().getProperty("custom.dbType");
        log.info("Provided database Type = {}", enabledDBType);
        return (enabledDBType != null && type != null && enabledDBType.equalsIgnoreCase(type));
    }
}
