package com.comrade.basics.flux;

import java.time.Duration;

import com.comrade.util.Util;

import reactor.core.publisher.Flux;

public class FluxIntervelClient {
    public static void main(String[] args) {
        Flux.interval(Duration.ofSeconds(1)).subscribe(Util.onNext);
        Util.threadSleep(100000);
    }
}
