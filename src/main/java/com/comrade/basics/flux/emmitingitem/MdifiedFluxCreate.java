package com.comrade.basics.flux.emmitingitem;

import com.comrade.util.Util;

import reactor.core.publisher.Flux;

public class MdifiedFluxCreate {
    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();
        Flux.create(nameProducer).subscribe(Util.subscriber());
        nameProducer.produce();

        Runnable runnable = nameProducer::produce;

        for (int index = 0; index < 10; index++) {
            new Thread(runnable).start();
        }
    }
}
