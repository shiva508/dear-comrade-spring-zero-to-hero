package com.comrade.basics.context;

import com.comrade.util.Util;

public class RateLimmiterClient {
    public static void main(String[] args) {
        BookService.getBook()
                .repeat(4)
                .contextWrite(UserService.getUserContextInfo())
                .subscribe(Util.subscriber());
    }
}
