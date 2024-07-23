package com.comrade.util;

public class UserUtil {
    public static void sleep(int seconds){
        try {
            Thread.sleep(seconds *1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
