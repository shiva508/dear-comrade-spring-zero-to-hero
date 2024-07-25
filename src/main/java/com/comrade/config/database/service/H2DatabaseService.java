package com.comrade.config.database.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class H2DatabaseService implements DatabaseService{
    @Override
    public void getConnectionDetails() {
        log.info("H2 database connection");
    }
}
