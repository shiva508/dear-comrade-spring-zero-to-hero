package com.comrade.azure.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationResponse implements Serializable {

    private String status;

    private String statusMsg;

    private boolean messageStatus;

    public NotificationResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public boolean isMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(boolean messageStatus) {
        this.messageStatus = messageStatus;
    }
}
