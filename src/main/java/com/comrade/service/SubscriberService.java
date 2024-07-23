package com.comrade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.comrade.model.Subscriber;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {
    List<Subscriber> subscribers = new ArrayList<>();
    AtomicInteger id = new AtomicInteger(0);

    public List<Subscriber> getAll() {
        return subscribers;
    }

    public void addSubscriber(String email) {
        subscribers.add(new Subscriber(id.addAndGet(1), email));
    }
}
