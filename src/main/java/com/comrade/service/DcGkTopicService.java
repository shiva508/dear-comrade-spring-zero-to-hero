package com.comrade.service;

public class DcGkTopicService extends GkTopicService{

    @Override
    public void createTopic() {
        super.createTopic();
    }

    @Override
    public void view() {
        System.out.println("START OF TRANSACTION");
        super.view();
        System.out.println("END OF TRANSACTION");
    }
}
