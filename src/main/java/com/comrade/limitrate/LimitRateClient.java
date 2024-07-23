package com.comrade.limitrate;

import com.comrade.util.Util;
import reactor.core.publisher.Flux;
public class LimitRateClient {
    public static void main(String[] args) {
        Flux.range(0, 100).log().limitRate(50, 25).subscribe(Util.subscriber());
    }
}
