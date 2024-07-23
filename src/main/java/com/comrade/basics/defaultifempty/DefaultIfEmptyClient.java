package com.comrade.basics.defaultifempty;

import com.comrade.util.Util;

import reactor.core.publisher.Flux;

public class DefaultIfEmptyClient {
    public static void main(String[] args) {
        getOrders().filter(num -> num > 10).defaultIfEmpty(408).subscribe(Util.subscriber());
    }

    public static Flux<Integer> getOrders() {
        return Flux.range(0, 10);
    }
}
