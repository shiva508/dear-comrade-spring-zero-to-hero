package com.comrade.basics.retry;

import com.comrade.util.Util;

import reactor.core.publisher.Flux;

public class RetryClient {

    public static void main(String[] args) {
        numberStream()
                .retry(3)
                .subscribe(Util.subscriber());
    }

    public static Flux<Integer> numberStream() {
        return Flux.range(0, 9)
                   .doOnSubscribe(num -> System.out.println("Subscribed"))
                   .doOnComplete(() -> System.out.println("**Completed"))
                   .map(num -> num / (Util.fakerInstance().random().nextInt(1, 5) > 3 ? 0 : 1))
                   .doOnError(err -> System.out.println("Error Occcured"));
    }
}
