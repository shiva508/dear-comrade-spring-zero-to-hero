package com.comrade.rabbitmq.producer;

import com.comrade.rabbitmq.model.NotificationRequest;
import com.comrade.rabbitmq.model.NotificationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationProducer {

    private final StreamBridge streamBridge;

    @Value("${producer.name}")
    String producerName;

    public NotificationResponse sendingNotification(NotificationRequest notificationRequest){
        NotificationResponse notificationResponse = new NotificationResponse();
        try {
            log.info("sendingNotification::started::{}",notificationRequest);
            boolean messageStatus = streamBridge.send(producerName, notificationRequest);
            notificationResponse.setMessageStatus(messageStatus);
            log.info("sendingNotification::completed::{}",notificationRequest);
        } catch (Exception exception){
            log.error("sendingNotification::input::{}::error::",notificationRequest,exception);
        }
        return notificationResponse;
    }
}
