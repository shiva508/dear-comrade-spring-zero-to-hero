package com.comrade.controller;

import com.comrade.config.DearComradeProperties;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
@AllArgsConstructor
public class ProfileController {
    private final Environment environment;
    private final DearComradeProperties dearComradeProperties;

    @GetMapping("/config")
    public Map<Object,Object> getConfiguration(){


        return Map.of("profiles",Arrays.stream(environment.getActiveProfiles()).toList(),
                "defaultProfiles", Arrays.stream(environment.getDefaultProfiles()).toList(),
                "appProfile",environment.getProperty("app.profile")==null?"":environment.getProperty("app.profile"));
    }

    @GetMapping("/model/config")
    public DearComradeProperties getModelConfiguration(){
        return dearComradeProperties;
    }
}
