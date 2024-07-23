package com.comrade.basics.flatmap;

import com.comrade.util.Util;

public class OrderClient {
    public static void main(String[] args) {
        UserService.users().flatMap(user -> OrderService.getOrdersByUserId(user.getUserId()))
                .subscribe(Util.subscriber());
    }
}
