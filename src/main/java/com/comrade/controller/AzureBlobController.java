package com.comrade.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/blob")
public class AzureBlobController {

    @GetMapping("/welcome")
    public String welcomeMessage(){
        return "Welcome";
    }

}
