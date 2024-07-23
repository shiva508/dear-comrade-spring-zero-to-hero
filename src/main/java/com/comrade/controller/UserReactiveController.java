package com.comrade.controller;


import com.comrade.entity.UserEntity;
import com.comrade.model.Input;
import com.comrade.service.UserReactiveService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reapi")
public class UserReactiveController {
    private final UserReactiveService userReactiveService;

    public UserReactiveController(UserReactiveService userReactiveService) {
        this.userReactiveService = userReactiveService;
    }

    @GetMapping("byname/{name}")
    public Mono<UserEntity> getUserEntityByName(@PathVariable String name){
        return userReactiveService.getUserByName(name);
    }

    @GetMapping("all")
    public Flux<UserEntity> getAllUserEntity(){
        return userReactiveService.getAllUsers();
    }

    @GetMapping(value = "reall",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<UserEntity> getAllUserEntityReactive(){
        return userReactiveService.getAllUsers();
    }

    @PostMapping("/createUser")
    public Mono<UserEntity> createUser(@RequestBody Mono<Input> inputMono,
                                       @RequestHeader Map<String,String> headers){
        System.out.println(headers);
        return userReactiveService.getCreateUser(inputMono);
    }

    @GetMapping("search")
    public Mono<UserEntity> getUserEntityByNameQueryParan(@RequestParam("name") String name){
        return userReactiveService.getUserByName(name);
    }
}
