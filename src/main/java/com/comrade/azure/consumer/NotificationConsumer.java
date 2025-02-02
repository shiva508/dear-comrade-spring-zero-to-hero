package com.comrade.azure.consumer;

import com.comrade.azure.model.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

@Component
@Slf4j
public class NotificationConsumer {

   @Bean
    public Consumer<Message<String>> consume(){
        return messages -> {
            ///messages.forEach(name -> {
                System.out.printf("name: %s %n",messages);
            //});
        };
    }
}
