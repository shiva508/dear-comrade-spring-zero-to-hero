package com.comrade.basics.combinepublisher.helper;

import com.comrade.util.Util;

import reactor.core.publisher.Flux;

public class Indigo {
    public static Flux<String> getFlights() {
        return Flux.range(1, Util.fakerInstance().random().nextInt(1, 10))
                .map(num -> "Indigo-" + Util.fakerInstance().random().nextInt(407, 509))
                .filter(flightName -> Util.fakerInstance().random().nextBoolean());
    }

}
