package com.comrade.controller;

import com.comrade.model.GreetingRequest;
import com.comrade.model.GreetingResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GreetingResponse sendMessage(GreetingRequest greetingRequest){
        log.info("sendMessage::message::{}",greetingRequest.getName());
        return new GreetingResponse(greetingRequest.getName());
    }
}
