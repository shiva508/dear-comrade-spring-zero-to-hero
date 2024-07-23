package com.comrade.basics.sink;

import com.comrade.util.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class SinkEmitClient {
    public static void main(String[] args) {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("Shiva"));
        sink.tryEmitValue("shhhhhh");
    }
}
