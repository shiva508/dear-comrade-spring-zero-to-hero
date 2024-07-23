package com.comrade.basics.flatmap;

import com.comrade.util.Util;

import lombok.Data;

@Data
public class PurchaseOrder {
    private String item;
    private String price;
    private Integer userId;

    public PurchaseOrder(Integer userId) {
        this.userId = userId;
        this.item = Util.fakerInstance().company().name();
        this.price = Util.fakerInstance().commerce().price();
    }

}
