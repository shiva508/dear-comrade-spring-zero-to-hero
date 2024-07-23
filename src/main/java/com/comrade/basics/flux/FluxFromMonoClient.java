package com.comrade.basics.flux;

import com.comrade.util.Util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxFromMonoClient {
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("A");
        Flux<String> flux = Flux.from(mono);
        flux.subscribe(Util.onNext);
    }
}
