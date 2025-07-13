package com.comrade.autoconfiguration;

import com.comrade.MilkySweetyLlmClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@AutoConfiguration
@ConditionalOnClass(MilkySweetyLlmClient.class)
@EnableConfigurationProperties(MilkySweetyLlmProperties.class)
public class MilkySweetyLlmAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean(MilkySweetyLlmConnectionDetails.class)
    public PropertiesMilkySweetyLlmConnectionDetails propertiesMilkySweetyLlmConnectionDetails(MilkySweetyLlmProperties  milkySweetyLlmProperties){
        System.out.println(milkySweetyLlmProperties);
        return new PropertiesMilkySweetyLlmConnectionDetails(milkySweetyLlmProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public MilkySweetyLlmClient client(MilkySweetyLlmConnectionDetails milkySweetyLlmConnectionDetails,
                                       ObjectProvider<RestClient.Builder> restClientBuilder,
                                       ObjectProvider<WebClient.Builder> webClientBuilder){
        return MilkySweetyLlmClient
                .builder()
                .baseUrl(milkySweetyLlmConnectionDetails.getBaseUrl())
                .restClientBuilder(restClientBuilder.getIfAvailable(RestClient::builder))
                .webClientBuilder(webClientBuilder.getIfAvailable(WebClient::builder))
                .build();

    }

}