package com.comrade.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = MilkySweetyLlmProperties.CONFIG_PREFIX)
public class MilkySweetyLlmProperties {

    public static final String CONFIG_PREFIX = "milky-sweety-llm";

    private String baseUrl = "http://localhost:8080";

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String toString() {
        return "MilkySweetyLlmProperties{" +
                "baseUrl='" + baseUrl + '\'' +
                '}';
    }
}
