package com.comrade.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "dc-ftp")
public class FtpProperties {

    private Integer port;

    private String adminName;

    private String username;

    private String password;

    private String host;

    private boolean ttlEnabled;

    private Long ttl;

    private String path;


}
