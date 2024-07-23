package com.comrade.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WelcomeController {

    @GetMapping("/welcome")
    public ResponseEntity<?> welcome() {
        log.info("Welcome");
        log.error("Something went wrong");
        return new ResponseEntity<>("Welcome", HttpStatus.OK);
    }
}
