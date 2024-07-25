package com.comrade.config.database.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MySqlDatabaseService implements DatabaseService{
    @Override
    public void getConnectionDetails() {
        log.info("MySql database connection");
    }
}
