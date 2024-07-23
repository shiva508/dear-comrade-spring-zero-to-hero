package com.comrade.basics.take;

import com.comrade.util.Util;

import reactor.core.publisher.Flux;

public class FluxCreateWithTakeClient {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            var countryName = "";
            do {
                countryName = Util.fakerInstance().country().name();
                System.out.println("Emmiting data: " + countryName);
                fluxSink.next(countryName);
            } while (!countryName.toLowerCase().equals("india") && !fluxSink.isCancelled());
            fluxSink.complete();
        }).take(8).subscribe(Util.subscriber());
    }
}
