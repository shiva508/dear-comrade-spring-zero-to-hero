package com.comrade.config.database;

import com.comrade.config.database.service.DatabaseService;
import com.comrade.config.database.service.H2DatabaseService;
import com.comrade.config.database.service.MongoDatabaseService;
import com.comrade.config.database.service.MySqlDatabaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseDriverConditionalConfig {

    @Bean(name = "h2DatabaseDriver")
    @DatabaseDriverType("H2")
    public DatabaseDriver h2DatabaseDriver(){
        return new H2DatabaseDriver();
    }

    @Bean(name = "mongoDatabaseDriver")
    @DatabaseDriverType("MONGO")
    public DatabaseDriver mongoDatabaseDriver(){
        return new MongoDatabaseDriver();
    }

    @Bean(name = "mySqlDatabaseDriver")
    @DatabaseDriverType("MYSQL")
    public DatabaseDriver mySqlDatabaseDriver(){
        return new MySqlDatabaseDriver();
    }

    @Bean(name = "h2DatabaseService")
    @Conditional(H2DriverDependencyValidator.class)
    public DatabaseService h2DatabaseService(){
        return new H2DatabaseService();
    }

    @Bean(name = "mongoDatabaseService")
    @Conditional(MongoDriverDependencyValidator.class)
    public DatabaseService mongoDatabaseService(){
        return new MongoDatabaseService();
    }

    @Bean(name = "mySqlDatabaseService")
    @Conditional(MySqlDriverDependencyValidator.class)
    public DatabaseService mySqlDatabaseService(){
        return new MySqlDatabaseService();
    }
}
