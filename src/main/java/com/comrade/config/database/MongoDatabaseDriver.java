package com.comrade.config.database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MongoDatabaseDriver implements DatabaseDriver{
    @Override
    public void driverName() {
        System.out.println("Mongo driver");
    }
}
