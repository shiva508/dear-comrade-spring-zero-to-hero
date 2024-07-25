package com.comrade.config.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
public class MySqlDriverDependencyValidator implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        /*
            String enabledDBType =context.getEnvironment().getProperty("dbType");
	        return (enabledDBType !=null && enabledDBType.equalsIgnoreCase("MYSQL"));
     */
        log.info("MySqlDriverDependencyValidator");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
