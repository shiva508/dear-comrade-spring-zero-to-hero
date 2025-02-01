package com.comrade.rabbitmq.consumer;

import com.comrade.rabbitmq.model.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@Slf4j
public class NotificationConsumer {

    @Bean
    public Consumer<List<NotificationRequest>> inbound(){
        return messages -> {
            messages.forEach(name -> {
                System.out.printf("name: %s %n",name.getName());
            });
        };
    }
}
