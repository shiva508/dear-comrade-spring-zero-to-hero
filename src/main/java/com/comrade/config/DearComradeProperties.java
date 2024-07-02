package com.comrade.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class DearComradeProperties {
    private String profile;
    private String level;
    private String emitPercent;
    private String noArgs;
    private Map<String, List<String>> helper;
    private String author;
}
