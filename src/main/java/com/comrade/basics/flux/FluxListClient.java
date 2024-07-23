package com.comrade.basics.flux;

import com.comrade.util.Util;

import reactor.core.publisher.Flux;

public class FluxListClient {
    public static void main(String[] args) {
        // List<String> names = Util.nameGenerator(10);
        // System.out.println(names);
        Flux<String> flux = Util.nameGeneratorreactive(10);
        flux.subscribe(Util.onNext);

    }
}
