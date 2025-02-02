package com.comrade.azure.controller;

import com.comrade.azure.model.NotificationRequest;
import com.comrade.azure.model.NotificationResponse;
import com.comrade.azure.producer.NotificationProducer;
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
@Slf4j
public class NotificationController {

    private final NotificationProducer notificationProducer;

    public NotificationController(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    @PostMapping("/send")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest notificationRequest){
        //log.info("sendNotification::started::input::{}",notificationRequest);
        NotificationResponse notificationResponse = notificationProducer.sendingNotification(notificationRequest);
        //log.info("sendNotification::completed::output::{}",notificationResponse);
        return new ResponseEntity<>(notificationResponse, HttpStatus.OK);
    }

}
