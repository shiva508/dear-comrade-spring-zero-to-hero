package com.comrade.controller;

import com.comrade.model.Welcome;
import com.comrade.service.GoodByeService;
import com.comrade.service.WelcomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WelcomeController {
    private final WelcomeService welcomeService;
    private final GoodByeService goodByeService;

    @GetMapping("/welcome")
    public Welcome welcomeMessage(){
        return welcomeService.welcomeMessage();
    }

    @GetMapping("/welcomeException")
    public Welcome welcomeMessageException(){
        return welcomeService.welcomeMessageException();
    }

    @GetMapping("/goodBye")
    public Welcome goodByeMessage(){
        return goodByeService.goodByeMessage();
    }

    @GetMapping("/goodByeException")
    public Welcome goodByeMessageException(){
        return goodByeService.goodByeMessageException();
    }
}
