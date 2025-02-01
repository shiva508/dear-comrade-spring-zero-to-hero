package com.comrade.rabbitmq.controller;

import com.comrade.rabbitmq.model.NotificationRequest;
import com.comrade.rabbitmq.model.NotificationResponse;
import com.comrade.rabbitmq.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationProducer notificationProducer;

    @PostMapping("/send")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("sendNotification::started::input::{}",notificationRequest);
        NotificationResponse notificationResponse = notificationProducer.sendingNotification(notificationRequest);
        log.info("sendNotification::completed::output::{}",notificationResponse);
        return new ResponseEntity<>(notificationResponse, HttpStatus.OK);
    }

}
