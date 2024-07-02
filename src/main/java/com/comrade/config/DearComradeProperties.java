package com.comrade.config;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class DearComradeProperties {
    private String profile;
    private String level;
    private String emitPercent;
    private String noArgs;
    private Map<String, List<String>> helper;
    private String author;

    @PostConstruct
    public void init(){
      log.info("DearComradeProperties");
    }
}
