package com.comrade.service;

import com.comrade.exception.DearComradeException;
import com.comrade.model.Welcome;
import org.springframework.stereotype.Component;

@Component
public class WelcomeService {

    public Welcome welcomeMessage(){
        return Welcome.builder().message("Welcome we my world").build();
    }

    public Welcome welcomeMessageException(){
        String name = null;
        throw new DearComradeException("Error");
    }
}
