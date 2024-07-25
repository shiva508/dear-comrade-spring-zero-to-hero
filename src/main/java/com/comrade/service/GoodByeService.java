package com.comrade.service;

import com.comrade.annotation.Timer;
import com.comrade.exception.DearComradeException;
import com.comrade.model.Welcome;
import org.springframework.stereotype.Component;

@Component
public class GoodByeService {

    @Timer
    public Welcome goodByeMessage(){
        return Welcome.builder().message("Good Bye to my world").build();
    }

    public Welcome goodByeMessageException(){
        String name = null;
        throw new DearComradeException("Good Bye to my world");
    }
}
