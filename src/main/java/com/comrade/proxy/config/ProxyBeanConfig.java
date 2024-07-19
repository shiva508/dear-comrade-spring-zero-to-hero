package com.comrade.proxy.config;


import com.comrade.proxy.DcNonProxyTransactionalBeanPostProcessor;
import com.comrade.proxy.DcProxyBeanFactoryInitializationAotProcessor;
import com.comrade.proxy.DcTransactionalBeanPostProcessor;
import com.comrade.service.GkTopicService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyBeanConfig {

    @Bean
    public GkTopicService gkTopicService() {
        return new GkTopicService();
    }

    @Bean
    public DcTransactionalBeanPostProcessor dcTransactionalBeanPostProcessor() {
        return new DcTransactionalBeanPostProcessor();
    }

    //@Bean
    public DcProxyBeanFactoryInitializationAotProcessor dcProxyBeanFactoryInitializationAotProcessor(){
        return new DcProxyBeanFactoryInitializationAotProcessor();
    }
    //@Bean
    public DcNonProxyTransactionalBeanPostProcessor dcNonProxyTransactionalBeanPostProcessor(){
        return new DcNonProxyTransactionalBeanPostProcessor();
    }
}
