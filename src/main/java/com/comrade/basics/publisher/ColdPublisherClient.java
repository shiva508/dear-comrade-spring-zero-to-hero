package com.comrade.basics.publisher;

import java.time.Duration;
import java.util.stream.Stream;

import com.comrade.util.Util;

import reactor.core.publisher.Flux;

public class ColdPublisherClient {
    public static void main(String[] args) {
        Flux<String> movie = Flux.fromStream(() -> getMovie()).delayElements(Duration.ofSeconds(1));
        movie.subscribe(Util.subscriber("Shiva"));
        Util.threadSleep(1000);
        movie.subscribe(Util.subscriber("Satish"));
        Util.threadSleep(10000);
    }

    public static Stream<String> getMovie() {
        return Stream.of("Batman", "Justic League", "Aquaman", "Superman", "Hulk");
    }
}
