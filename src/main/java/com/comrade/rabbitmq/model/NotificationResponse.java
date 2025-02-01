package com.comrade.rabbitmq.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationResponse implements Serializable {

    private String status;

    private String statusMsg;

    private boolean messageStatus;
}
