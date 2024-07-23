package com.comrade.basics.backprusure;

import com.comrade.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class BackpressureDropClient {

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16");
        Flux.create(fluxSink -> {
            for (int index = 0; index < 508; index++) {
                System.out.println("Publish : " + index);
                fluxSink.next(index);
                Util.threadSleep(1);
            }
            fluxSink.complete();

        })
                .onBackpressureDrop()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(num -> Util.threadSleep(10))
                .subscribe(Util.subscriber());
        Util.threadSleep(10000);

    }
}
