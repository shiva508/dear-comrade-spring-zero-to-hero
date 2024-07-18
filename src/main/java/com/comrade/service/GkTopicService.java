package com.comrade.service;

import com.comrade.annotations.DcTransaction;

public class GkTopicService implements TopicService {

    //@DcTransaction
    @Override
    public void createTopic() {
        System.out.println("Create topic");
    }

    @Override
    public void view() {
        System.out.println("View Topic");
    }
}
